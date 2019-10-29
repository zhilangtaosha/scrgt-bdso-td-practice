package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;

public interface MoviedbService {

    List<MoviedbActorDocument> getActorInfoByName(String name);
    
    MoviedbActorDocument getActorInfo(String id);
    
    Iterable<MoviedbActorDocument> getAllCelebrities();
    
    MoviedbActorDocument addActor(MoviedbActorDocument dto) throws BusinessException;
    
    void deleteActor(String id);
    
    List<MoviedbMovieDocument> getMovieInfoByTitle(String title);
    
    MoviedbMovieDocument getMovieInfo(String id);
    
    Iterable<MoviedbMovieDocument> getAllMovies();
    
    MoviedbMovieDocument addMovie(MoviedbMovieDocument dto) throws BusinessException;
    
    void deleteMovie(String id);
}
