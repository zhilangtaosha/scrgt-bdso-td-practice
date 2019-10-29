package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.dozermapper.core.Mapper;

import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

@RunWith(MockitoJUnitRunner.class)
public class MovieConverterTest {
	
	@InjectMocks
	MovieConverter movieConverter;
	
	Mapper mapper = Mockito.mock(Mapper.class);
	
 
	@Test
	public void testConvertTo() throws Exception{
		
		Movies movies = new Movies();
		movies.setId(100);
		Movie movie = new Movie();
		Mockito.when(mapper.map(movies, Movie.class)).thenReturn(movie);
		Movie returnObject = movieConverter.convertTo(movies, movie);
		assertNotNull(returnObject);
	 
		
	}
	 
	@Test
	public void testConvertFrom() throws Exception{
		
		Movie movie = new Movie();
		movie.setId(100);
		Movies movies = new Movies();
		Mockito.when(mapper.map(movie, Movies.class)).thenReturn(movies);
		Movies returnObject = movieConverter.convertFrom(movie, movies);
		assertNotNull(returnObject);
		
	}
	 
 
	@SuppressWarnings("static-access")
	@Test
	public void testConvertFromMovies() throws Exception{
		
		Movie movie = new Movie();
		movie.setId(100);
		Actors actors = new Actors();
		Movies movies = new Movies();
		Set<MovieGenres> movieGenresSet = new HashSet<MovieGenres>();
		MovieGenres movieGen = new MovieGenres();
		movieGenresSet.add(movieGen);
		movies.setGenres(movieGenresSet);
		
		Set<MovieCast> movieCastSet = new HashSet<MovieCast>();
		MovieCast movieCast = new MovieCast();
		movieCastSet.add(movieCast);
		movies.setCast(movieCastSet);
		
		Mockito.when(mapper.map(movie, Movies.class)).thenReturn(movies);
		Movies returnObject = movieConverter.convertFromMovies(mapper,movie, actors);
		assertNotNull(returnObject);
		
	}
	
	
}
