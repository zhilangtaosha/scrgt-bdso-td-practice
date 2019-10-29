package gov.dhs.uscis.bdso.celebrity.service.impl;

 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.config.MapperConfiguration;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.repository.ActorsRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MovieCastRepository;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;
import gov.dhs.uscis.bdso.celebrity.service.ImageService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;

@Service
public class CelebrityServiceImpl implements CelebrityService {
	private final Logger logger = LoggerFactory.getLogger(CelebrityServiceImpl.class);
    private static final String IMAGE_PATH_KEY_PREFIX = "app/celebrity/images";

    @Inject
	private ActorsRepository actorsRepository;

    @Inject
    private MovieCastRepository movieCastRepository;

    @Inject
    private Mapper mapper;

    @Inject
    private S3Service s3Service;

    @Inject
    private ImageService imageService;

	@Override
	@Transactional
	public List<Celebrity> getCelebrityInfoByName(String name) {
		List<Actors> actors = actorsRepository.findActorsByName(name);
		List<Celebrity> dtoList = new ArrayList<>();
        actors.forEach(actor -> dtoList.add(mapActorToCelebrity(actor)));
		return dtoList;
	}
	
	@Override
	@Transactional
	public Celebrity getCelebrityInfo(int id) {
		Optional<Actors> actor = actorsRepository.findById(id);

        if (actor.isPresent()) {
            return mapActorToCelebrity(actor.get());
        }

        return null;
	}
	
	@Override
	public Celebrity getCelebrityInfoByImdbId(String id) {
		Actors actor = actorsRepository.findActorsByImdbId(id);

        if (actor != null) {
            return mapActorToCelebrity(actor);
        }
		return null;
	}

	@Override
	public Celebrity getCelebrityInfoByMovdbId(String id) {
		Actors actor = actorsRepository.findActorsByMovdbId(id);

        if (actor != null) {
            return mapActorToCelebrity(actor);
        }
		return null;
	}

	@Override
	@Transactional
	public List<Celebrity> getAllCelebrities() {
		Iterable<Actors> actors = actorsRepository.findAll();
		List<Celebrity> dtoList = new ArrayList<>();
        actors.forEach(actor -> dtoList.add(mapActorToCelebrity(actor, MapperConfiguration.MAP_ID_MINIMAL)));
		return dtoList;
	}
	
	@Override
	@Transactional
	public List<Celebrity> getCelebritiesByActorIds(String[] actorIds) {
		List<Celebrity> dtoList = new ArrayList<>();
		
		for (String actorId : actorIds) {
			Actors actor = actorsRepository.findActorsByMovdbId(actorId);
			dtoList.add(mapActorToCelebrity(actor, MapperConfiguration.MAP_ID_MINIMAL));
		}
		return dtoList;
	}
	
	@Override
	@Transactional
	public List<Celebrity> getCelebritiesByMovieIds(String[] movieIds) {
		List<Celebrity> dtoList = new ArrayList<>();
        List<Actors> actors = actorsRepository.findActorsByMovieIds(Arrays.asList(movieIds));
		for (Actors actor : actors) {
			dtoList.add(mapActorToCelebrity(actor, MapperConfiguration.MAP_ID_MINIMAL));
		}
		return dtoList;
	}
	
	@Override
	@Transactional
	public JSONArray getCelebritiesByActorsAndMovies(String[] actorIds, String[] movieIds) {
		List<Celebrity> data = getCelebritiesByActorIds(actorIds);
    	List<Celebrity> addData = getCelebritiesByMovieIds(movieIds);
    	
    	for (Celebrity actor : addData) {
    		if (!data.contains(actor)) {
                data.add(actor);
            }
    	}
    	
    	JSONArray celebrities = new JSONArray();
		try {
			for (Celebrity actor : data) {
				JSONObject obj = new JSONObject();
				obj.put("firstName", actor.getFirstName());
				obj.put("lastName", actor.getLastName());
				obj.put("imageUrl", actor.getImageUrl());
				celebrities.put(obj);
			}
		}
		catch(JSONException e) {
			logger.error("Error retrieving data", e);
		}
    	
		return celebrities;
	}

	@Override
	@Transactional
    public Celebrity addCelebrity(Celebrity dto) throws BusinessException {
        Actors actor = mapCelebrityToActor(dto, null);
        actor = actorsRepository.save(actor);
        return mapActorToCelebrity(actor);
	}

	@Override
    public void addCelebrities(List<Celebrity> dtos) throws BusinessException {
        List<Actors> actors = new ArrayList<>();
        dtos.forEach(dto -> actors.add(mapCelebrityToActor(dto, null)));

        if (!actors.isEmpty()) {
            actorsRepository.saveAll(actors);
        }
    }

    @Override
	@Transactional
	public Celebrity updateCelebrity(Celebrity dto) throws BusinessException {
				Actors dbActor = actorsRepository.getOne(dto.getId());
				if (dbActor!=null) {
					dto.setImageUrl(dbActor.getImageUrl());
				}
        Actors actor = mapCelebrityToActor(dto, dbActor);
        Set<MovieCast> movieCasts = actor.getMovieCast();
				movieCastRepository.saveAll(movieCasts);
        actor = actorsRepository.save(actor);

        return mapActorToCelebrity(actor);
	}

    private Actors mapCelebrityToActor(Celebrity celeb, Actors actor) {
        return mapCelebrityToActor(celeb, actor, null);
    }

    private Actors mapCelebrityToActor(Celebrity celeb, Actors actor, String mapId) {
        String s3Key = null;
        String base64Image = celeb.getImage();

        if (base64Image != null) {
            byte[] image = Base64.getDecoder().decode(base64Image);
            s3Key = s3Service.put(IMAGE_PATH_KEY_PREFIX + "/" + UUID.randomUUID().toString(), image);
        }

        Actors retVal = actor == null ? new Actors() : actor;

        if (mapId != null) {
            mapper.map(celeb, retVal, mapId);
        } else {
            mapper.map(celeb, retVal);
        }

        if (s3Key != null) {
            retVal.setImageUrl(s3Key);
        }
        return retVal;
    }

    private Celebrity mapActorToCelebrity(Actors actor) {
        return mapActorToCelebrity(actor, null);
    }

    private Celebrity mapActorToCelebrity(Actors actor, String mapId) {
        Celebrity celeb = null;

        if (mapId == null) {
            celeb = mapper.map(actor, Celebrity.class);
        } else {
            celeb = mapper.map(actor, Celebrity.class, mapId);
        }
        
        String imageUrl = imageService.getImageAsUrl(celeb.getImageUrl());

        celeb.setImageUrl(imageUrl);
        return celeb;
    }

}