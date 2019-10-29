package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;

public interface ImdbService {

    List<ImdbActorDocument> getActorInfoByName(String name);
    
    ImdbActorDocument getActorInfo(String id);
    
    Iterable<ImdbActorDocument> getAllCelebrities();
    
    ImdbActorDocument addActor(ImdbActorDocument dto) throws BusinessException;
    
    void deleteActor(String id);
    
    List<ImdbMovieDocument> getMovieInfoByTitle(String title);
    
    ImdbMovieDocument getMovieInfo(String id);
    
    Iterable<ImdbMovieDocument> getAllMovies();
    
    ImdbMovieDocument addMovie(ImdbMovieDocument dto) throws BusinessException;
    
    void deleteMovie(String id);
}
