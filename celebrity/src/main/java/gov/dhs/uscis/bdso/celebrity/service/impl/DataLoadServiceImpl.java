package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiMovieDocument;
import gov.dhs.uscis.bdso.celebrity.enumeration.Sources;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.service.CastDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.DataLoadService;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MovieDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@Named
public class DataLoadServiceImpl implements DataLoadService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String CELEBRITY = "person";
    private static final String MOVIES = "movies";

    @Inject
    private S3Service s3Service;

    @Inject
    private ImdbService imdbService;

    @Inject
    private MoviedbService moviedbService;

    @Inject
    private WikipediaService wikipediaService;
    
    @Inject
    private CelebrityDataMapperService celebrityDataMapperService;

    @Inject
    private MovieDataMapperService movieDataMapperService;

    @Inject
    private CastDataMapperService caseDataMapperService;
    
    @Override
    @Transactional
    public void triggerLoad() throws BusinessException {
        loadCelebrities(Sources.IMDB);
        loadCelebrities(Sources.THE_MOVIE_DB);
        loadCelebrities(Sources.WIKIPEDIA);
        
        loadMovies(Sources.THE_MOVIE_DB);
        loadMovies(Sources.WIKIPEDIA);
        
        celebrityDataMapperService.mapAllCelebrities();
        movieDataMapperService.mapAllMovies();
        caseDataMapperService.mapAllMovieCast();
        
    }

    @Override
    public void loadCelebrities(Sources source) throws BusinessException {
        switch (source) {
            case IMDB:
                List<ImdbActorDocument> imdbActorDocuments =
                        loadDataSource(CELEBRITY + "/" + source, ImdbActorDocument.class);

                for (ImdbActorDocument document : imdbActorDocuments) {
                	LOG.info("Processing IMDB actor ID - {}", document.getId());
                    imdbService.addActor(document);
                }
                break;
            case THE_MOVIE_DB:
                List<MoviedbActorDocument> moviedbActorDocuments =
                        loadDataSource(CELEBRITY + "/" + source, MoviedbActorDocument.class);

                for (MoviedbActorDocument document : moviedbActorDocuments) {
                	LOG.info("Processing moviedb actor ID - {}", document.getId());
                    moviedbService.addActor(document);
                }
                break;
            case WIKIPEDIA:
                List<WikiActorDocument> wikiActorDocuments =
                        loadDataSource(CELEBRITY + "/" + source, WikiActorDocument.class);

                for (WikiActorDocument document : wikiActorDocuments) {
                	LOG.info("Processing wikipedia actor ID - {}", document.getId());
                    wikipediaService.addActor(document);
                }
                break;
        }
    }

    @Override
    public void loadMovies(Sources source) throws BusinessException {
    	switch (source) {
        case IMDB:
            List<ImdbMovieDocument> imdbMovieDocuments =
                    loadDataSource(MOVIES + "/" + source, ImdbMovieDocument.class);

            for (ImdbMovieDocument document : imdbMovieDocuments) {
            	LOG.info("Processing IMDB Movie ID - {}", document.getId());
                imdbService.addMovie(document);
            }
            break;
        case THE_MOVIE_DB:
            List<MoviedbMovieDocument> moviedbMovieDocuments =
                    loadDataSource(MOVIES + "/" + source, MoviedbMovieDocument.class);

            for (MoviedbMovieDocument document : moviedbMovieDocuments) {
            	LOG.info("Processing moviedb Movie ID - {}", document.getId());
                moviedbService.addMovie(document);
            }
            break;
        case WIKIPEDIA:
            List<WikiMovieDocument> wikiMovieDocuments =
                    loadDataSource(MOVIES + "/" + source, WikiMovieDocument.class);

            for (WikiMovieDocument document : wikiMovieDocuments) {
            	LOG.info("Processing wikipedia Movie ID - {}", document.getMovieID());
                wikipediaService.addMovie(document);
            }
            break;
        }
    }
    
    private <T> List<T> loadDataSource(String prefix, Class<T> docClass) {
        return s3Service.loadDataSource(prefix, docClass);
      
    }

}
