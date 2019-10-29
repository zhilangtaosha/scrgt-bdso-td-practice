package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;

import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

public interface MovieService {
	
	Movie getMovieInfoByTitle(String title);
	
	Movie getMovieInfoById(int id);
	
	Movie getMovieInfoByImdbId(String id);
	
	Movie getMovieInfoByMovdbId(String id);
	
    List<Movie> getAllMoviesInfoByTitle(String title);

    List<Movie> getAllMovies();
    
    Movie addMovie(Movie dto) throws BusinessException;
    
    Movie updateMovie(Movie dto) throws BusinessException;
    
}
