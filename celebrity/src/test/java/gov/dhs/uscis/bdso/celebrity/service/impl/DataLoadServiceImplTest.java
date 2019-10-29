package gov.dhs.uscis.bdso.celebrity.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MovieDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;


@RunWith(MockitoJUnitRunner.class)
public class DataLoadServiceImplTest {
    private static final String CELEBRITY = "person";
    private static final String MOVIES = "movies";

    @InjectMocks
    private DataLoadServiceImpl dataLoadService;

    @Mock
    private S3Service s3Service;

    @Mock
    private ImdbService imdbService;

    @Mock
    private MoviedbService moviedbService;

    @Mock
    private WikipediaService wikipediaService;

    @Mock
    private CelebrityDataMapperService celebrityDataMapperService;

    @Mock
    private MovieDataMapperService movieDataMapperService;

    @Mock
    private CastDataMapperService caseDataMapperService;

    @Test
    public void testTriggerLoad() throws BusinessException {
        ImdbActorDocument imdbDoc = new ImdbActorDocument();
        List<ImdbActorDocument> imdbDocuments = new ArrayList<>();
        imdbDocuments.add(imdbDoc);

        MoviedbActorDocument moviedbDoc = new MoviedbActorDocument();
        List<MoviedbActorDocument> moviedbDocuments = new ArrayList<>();
        moviedbDocuments.add(moviedbDoc);

        WikiActorDocument wikiDoc = new WikiActorDocument();
        List<WikiActorDocument> wikiDocuments = new ArrayList<>();
        wikiDocuments.add(wikiDoc);

        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.IMDB, ImdbActorDocument.class))
                .willReturn(imdbDocuments);
        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.THE_MOVIE_DB, MoviedbActorDocument.class))
                .willReturn(moviedbDocuments);
        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.WIKIPEDIA, WikiActorDocument.class))
                .willReturn(wikiDocuments);
        dataLoadService.triggerLoad();
        verify(imdbService).addActor(any(ImdbActorDocument.class));
        verify(moviedbService).addActor(any(MoviedbActorDocument.class));
        verify(wikipediaService).addActor(any(WikiActorDocument.class));
        verify(s3Service, times(5)).loadDataSource(anyString(), any());
    }

    @Test
    public void testLoadCelebritiesFromImdb() throws BusinessException {
        ImdbActorDocument imdbDoc = new ImdbActorDocument();
        List<ImdbActorDocument> imdbDocuments = new ArrayList<>();
        imdbDocuments.add(imdbDoc);
        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.IMDB, ImdbActorDocument.class))
                .willReturn(imdbDocuments);
        dataLoadService.loadCelebrities(Sources.IMDB);
        verify(imdbService).addActor(any(ImdbActorDocument.class));
        verify(s3Service).loadDataSource(CELEBRITY + "/" + Sources.IMDB, ImdbActorDocument.class);
    }

    @Test
    public void testLoadCelebritiesFromMovieDb() throws BusinessException {
        MoviedbActorDocument doc = new MoviedbActorDocument();
        List<MoviedbActorDocument> documents = new ArrayList<>();
        documents.add(doc);
        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.THE_MOVIE_DB, MoviedbActorDocument.class))
                .willReturn(documents);
        dataLoadService.loadCelebrities(Sources.THE_MOVIE_DB);
        verify(moviedbService).addActor(any(MoviedbActorDocument.class));
        verify(s3Service).loadDataSource(CELEBRITY + "/" + Sources.THE_MOVIE_DB, MoviedbActorDocument.class);
    }

    @Test
    public void testLoadCelebritiesFromWikipedia() throws BusinessException {
        WikiActorDocument doc = new WikiActorDocument();
        List<WikiActorDocument> documents = new ArrayList<>();
        documents.add(doc);
        given(s3Service.loadDataSource(CELEBRITY + "/" + Sources.WIKIPEDIA, WikiActorDocument.class))
        .willReturn(documents);
        dataLoadService.loadCelebrities(Sources.WIKIPEDIA);
        verify(wikipediaService).addActor(any(WikiActorDocument.class));
        verify(s3Service).loadDataSource(CELEBRITY + "/" + Sources.WIKIPEDIA, WikiActorDocument.class);
    }

    @Test
    public void testLoadMoviesFromImdb() throws BusinessException {
        ImdbMovieDocument doc = new ImdbMovieDocument();
        List<ImdbMovieDocument> documents = new ArrayList<>();
        documents.add(doc);
        given(s3Service.loadDataSource(MOVIES + "/" + Sources.IMDB, ImdbMovieDocument.class)).willReturn(documents);
        dataLoadService.loadMovies(Sources.IMDB);
        verify(imdbService).addMovie(any(ImdbMovieDocument.class));
        verify(s3Service).loadDataSource(MOVIES + "/" + Sources.IMDB, ImdbMovieDocument.class);
    }
    
    @Test
    public void testLoadMoviesFromMoviedb() throws BusinessException {
        MoviedbMovieDocument doc = new MoviedbMovieDocument();
        List<MoviedbMovieDocument> documents = new ArrayList<>();
        documents.add(doc);
        given(s3Service.loadDataSource(MOVIES + "/" + Sources.THE_MOVIE_DB, MoviedbMovieDocument.class)).willReturn(documents);
        dataLoadService.loadMovies(Sources.THE_MOVIE_DB);
        verify(moviedbService).addMovie(any(MoviedbMovieDocument.class));
        verify(s3Service).loadDataSource(MOVIES + "/" + Sources.THE_MOVIE_DB, MoviedbMovieDocument.class);
    }
    
    @Test
    public void testLoadMoviesFromWikipedia() throws BusinessException {
        WikiMovieDocument doc = new WikiMovieDocument();
        List<WikiMovieDocument> documents = new ArrayList<>();
        documents.add(doc);
        given(s3Service.loadDataSource(MOVIES + "/" + Sources.WIKIPEDIA, WikiMovieDocument.class)).willReturn(documents);
        dataLoadService.loadMovies(Sources.WIKIPEDIA);
        verify(wikipediaService).addMovie(any(WikiMovieDocument.class));
        verify(s3Service).loadDataSource(MOVIES + "/" + Sources.WIKIPEDIA, WikiMovieDocument.class);
    }

}
