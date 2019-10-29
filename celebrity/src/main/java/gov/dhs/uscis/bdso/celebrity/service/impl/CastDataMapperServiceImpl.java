package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.lang.invoke.MethodHandles;
import java.time.ZoneId;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorCast;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.repository.ActorsRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MovieCastRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MoviesRepository;
import gov.dhs.uscis.bdso.celebrity.service.CastDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;

@Service
public class CastDataMapperServiceImpl implements CastDataMapperService {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private MoviedbService moviedbService;
	
	@Autowired
	private ActorsRepository actorsRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
	private MovieCastRepository movieCastRepository;
	
	@Override
	@Transactional
	public void mapAllMovieCast() {
		Iterable<MoviedbActorDocument> actorList = moviedbService.getAllCelebrities();
		actorList.forEach(actor -> {
			LOG.info("Mapping actor ID for cast - {}", actor.getId());
			mapMovieCastData(actor);
		});
	}
	
	protected void mapMovieCastData(MoviedbActorDocument actorInfo) {
		
		if (CollectionUtils.isEmpty(actorInfo.getCast())) {
            return;
        }
		
		//Retrieve actor by movdbid
		Actors actor = actorsRepository.findActorsByMovdbId(actorInfo.getId());
		if (actor == null) {
            return;
        }
		
		for (MoviedbActorCast cast : actorInfo.getCast()) {
			//Retrieve movie by movdbid
			Movies movie = moviesRepository.findByMovdbId(cast.getId());
			if(movie == null) {
				//Try retrieving by title
				movie = moviesRepository.findByTitle(cast.getTitle());
			}
			if(movie == null) {
				//add the movie
				movie = new Movies();
				movie.setTitle(cast.getTitle());
                	
				movie.setReleaseDate(cast.getReleaseDate() != null ? 
							cast.getReleaseDate().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDate() : null);
				movie.setOverview(cast.getOverview());
				movie.setOrigTitle(cast.getOriginalTitle());
				movie.setTitle(cast.getTitle());
				movie.setOrigLanguage(cast.getOriginalLanguage());
				movie.setMovdbId(cast.getId());
				
				movie = moviesRepository.save(movie);
			}

			MovieCast movieCast = movieCastRepository.findByActorIdAndMovieId(
																actor.getId(),
																movie.getId());
			if(movieCast != null) {
                continue;
            }
			
			MovieCast castRecord = new MovieCast();
            castRecord.setActorId(actor.getId());
            castRecord.setMovieId(movie.getId());
			castRecord.setRoleName(cast.getCharacter());
			
			movieCastRepository.save(castRecord);

		}		
		
	}
	

}
