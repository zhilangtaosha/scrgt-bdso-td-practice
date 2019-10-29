package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;

import org.json.JSONArray;

import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;

public interface CelebrityService {
	
    List<Celebrity> getCelebrityInfoByName(String name);
    
    Celebrity getCelebrityInfo(int id);
    
    Celebrity getCelebrityInfoByImdbId(String id);
    
    Celebrity getCelebrityInfoByMovdbId(String id);
    
    List<Celebrity> getAllCelebrities();
    
    List<Celebrity> getCelebritiesByActorIds(String[] actorIds);
    
    List<Celebrity> getCelebritiesByMovieIds(String[] movieIds);
    
    JSONArray getCelebritiesByActorsAndMovies(String[] actorIds, String[] movieIds);
    
    Celebrity addCelebrity(Celebrity dto) throws BusinessException;
    
    void addCelebrities(List<Celebrity> dto) throws BusinessException;

    Celebrity updateCelebrity(Celebrity dto) throws BusinessException;
    
}
