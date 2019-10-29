package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;

public interface WikipediaService {

    List<WikiActorDocument> getActorInfoByName(String name);
    
    WikiActorDocument getActorInfo(String id);
    
    Iterable<WikiActorDocument> getAllCelebrities();
    
    WikiActorDocument addActor(WikiActorDocument dto) throws BusinessException;
    
    void deleteActor(String id);
    
    WikiMovieDocument getMovieInfo(String id);
    
    Iterable<WikiMovieDocument> getAllMovies();
    
    WikiMovieDocument addMovie(WikiMovieDocument dto) throws BusinessException;
    
    void deleteMovie(String id);
}
