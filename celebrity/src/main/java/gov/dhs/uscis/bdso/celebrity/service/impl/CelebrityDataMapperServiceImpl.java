package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorImage;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.model.Gender;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@Service
public class CelebrityDataMapperServiceImpl implements CelebrityDataMapperService {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String IMG_KEY_PREFIX = "person/images/";
	private static final String IMG_KEY_STR = "/themoviedb_i_";
	private static final String IMG_TARGET_LOC = "app/celebrity/images/";
	
	@Autowired
	private ImdbService imdbService;
	
	@Autowired
	private MoviedbService moviedbService;
	
	@Autowired
	private WikipediaService wikipediaService;
	
	@Autowired
	private CelebrityService celebrityService;
	
	@Autowired
	private S3Service s3Service;
	
	@Override
	@Transactional
	public void mapAllCelebrities() throws BusinessException {
		Iterable<ImdbActorDocument> actorList = imdbService.getAllCelebrities();
		actorList.forEach(actor -> {
			try {
				LOG.info("Mapping celebrity ID - {}", actor.getId());
				mapCelebrityData(actor.getId());
			} catch (BusinessException e) {
				LOG.error("Error mapping data to postgres for id - {}", actor.getId(), e);
			}
		});
	}
	
	public void mapCelebrityData(String id) throws BusinessException {
		
		//Fetch the data elements from all the different ES Indexes
		ImdbActorDocument imdbInfo = imdbService.getActorInfo(id);
		MoviedbActorDocument movieDbInfo = moviedbService.getActorInfo(id);
		
		LOG.info("IMDB Actor info - {}", imdbInfo.getId());
		LOG.info("MovieDB Actor info - {}", movieDbInfo.getId());

		Celebrity dto = celebrityService.getCelebrityInfoByImdbId(id);
		if (dto != null)
         {
            return; //if data already loaded then skip
        }
		
		dto = new Celebrity();
		transformImdbActorData(imdbInfo, dto);
		transformMoviedbActorData(movieDbInfo, dto);
		
		celebrityService.addCelebrity(dto);
		
	}
	
	private void transformImdbActorData(ImdbActorDocument imdbInfo, Celebrity dto) {
		dto.setBiography(!CollectionUtils.isEmpty(imdbInfo.getMiniBiography()) ? 
									imdbInfo.getMiniBiography().get(0) : null);
		dto.setBirthPlace(imdbInfo.getBirthNotes());
		dto.setDob(imdbInfo.getBirthDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate());
		dto.setBirthName(imdbInfo.getBirthName());
		
		dto.setAverageBoxOffice(null);
		dto.setHeight(imdbInfo.getHeight());
		dto.setImdbId(imdbInfo.getId());
		dto.setNickName(!CollectionUtils.isEmpty(imdbInfo.getNickNames()) ?
							imdbInfo.getNickNames().get(0) : null);
		setTotalBoxOFfice(imdbInfo, dto);
	}
	
	private void setTotalBoxOFfice(ImdbActorDocument imdbInfo, Celebrity dto) {
		long totalSalary = 0;
		if (CollectionUtils.isEmpty(imdbInfo.getSalaryHistory())) {
            return;
        }
		for (String salaryStr : imdbInfo.getSalaryHistory()) {
			String salary = salaryStr.split("::")[1];
            if (salary != null) {
                salary = StringUtils.remove(StringUtils.remove(salary, '$'), ',');

				if (NumberUtils.isCreatable(salary)) {
                    totalSalary = totalSalary + Long.valueOf(salary);
                }
            }
		}
		dto.setTotalBoxOffice(new BigDecimal(totalSalary));
		
	}
	
	private void setNames(String birthName, Celebrity dto) {
		if (birthName != null) {
			String[] names = birthName.split(" ", 2);
			dto.setFirstName(names[0]);
			if (names.length > 1) {
                dto.setLastName(names[1]);
            }
		}
	}
	
	private void transformMoviedbActorData(MoviedbActorDocument movieDbInfo, Celebrity dto) {
		setNames(movieDbInfo.getName(), dto);
		dto.setGender(movieDbInfo.getGender() == 1 ? Gender.FEMALE : Gender.MALE);
		dto.setPopularity(movieDbInfo.getPopularity());
		dto.setBiography(movieDbInfo.getBiography());
		dto.setMovdbId(movieDbInfo.getId());
		
		setImageUrl(movieDbInfo, dto);
	}

	private void setImageUrl(MoviedbActorDocument movieDbInfo, Celebrity dto) {
		if (CollectionUtils.isEmpty(movieDbInfo.getImages())) {
            return;
        }
		
		MoviedbActorImage image = movieDbInfo.getImages().get(0);
		String key = new StringBuilder().append(IMG_KEY_PREFIX)
										.append(movieDbInfo.getId())
										.append(IMG_KEY_STR)
										.append(movieDbInfo.getId())
										.append("_")
										.append(image.getFile_path().replaceAll("/", ""))
										.toString();
				
		byte[] imageBytes = s3Service.getAsBytes(key);
		
		String newKey = s3Service.put(IMG_TARGET_LOC + UUID.randomUUID().toString(), imageBytes);
		
		dto.setImageUrl(newKey);
	}
}
