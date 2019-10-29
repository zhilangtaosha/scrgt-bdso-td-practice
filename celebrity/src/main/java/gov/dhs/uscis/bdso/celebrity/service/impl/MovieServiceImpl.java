package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Movie;
import gov.dhs.uscis.bdso.celebrity.repository.MoviesRepository;
import gov.dhs.uscis.bdso.celebrity.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MoviesRepository moviesRepository;

    @Autowired
    private Mapper mapper;

	@Override
	@Transactional
	public Movie getMovieInfoByTitle(String title) {
		Movies movie = moviesRepository.findByTitle(title);
		return mapper.map(movie, Movie.class);
	}
	
	@Override
	@Transactional
	public Movie getMovieInfoById(int id) {
		Optional<Movies> movie = moviesRepository.findById(id);
		Movie dto = null;
		if (movie.isPresent()) {
			dto = mapper.map(movie.get(), Movie.class);
		}
		return dto;
	}

	@Override
	@Transactional
	public Movie getMovieInfoByImdbId(String id) {
		Movies movie = moviesRepository.findByImdbId(id);
		Movie dto = null;
		if (movie != null) {
			dto = mapper.map(movie, Movie.class);
		}
		return dto;
	}

	@Override
	@Transactional
	public Movie getMovieInfoByMovdbId(String id) {
		Movies movie = moviesRepository.findByMovdbId(id);
		Movie dto = null;
		if (movie != null) {
			dto = mapper.map(movie, Movie.class);
		}
		return dto;
	}
	
	@Override
	@Transactional
	public List<Movie> getAllMoviesInfoByTitle(String title) {
		List<Movies> list = moviesRepository.findMoviesByTitle(title);
		List<Movie> dtoList = new ArrayList<>();
		list.forEach(movie -> 
			dtoList.add(mapper.map(movie, Movie.class)));
		return dtoList;
	}

	@Override
	@Transactional
	public List<Movie> getAllMovies() {
		Iterable<Movies> list = moviesRepository.findAll();
		List<Movie> dtoList = new ArrayList<>();
        list.forEach(movie -> dtoList.add(mapper.map(movie, Movie.class)));
		return dtoList;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie dto) throws BusinessException {
		//Check if the movie title is null
		Validate.notNull(dto.getTitle(), "Movie title cannot be null");
		
		Movies movie = new Movies();
		mapper.map(dto, movie);
		movie = moviesRepository.save(movie);
		
		return mapper.map(movie, Movie.class);
	}

	@Override
	public Movie updateMovie(Movie dto) throws BusinessException {
		//Check if the movie title is null
		Validate.notNull(dto.getTitle(), "Movie title cannot be null");
		
		Movies movie = moviesRepository.findById(dto.getId()).get();
		movie.getGenres().clear();
		movie.getCompanies().clear();
		mapper.map(dto, movie);
		
		movie = moviesRepository.save(movie);
		
		return mapper.map(movie, Movie.class);
	}

}