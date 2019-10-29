package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Cast;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.model.Filmography;
import gov.dhs.uscis.bdso.celebrity.model.Genre;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityActorsGenreConverterTest {
	Mapper mapper = Mockito.mock(Mapper.class);
	
    @InjectMocks
    CelebrityActorsGenreConverter celebrityActorsConverter = new CelebrityActorsGenreConverter();
	
	Actors actors = new Actors();
	
	Celebrity celebrity = new Celebrity();
	
    Filmography filmography = null;
	
	@Before
	public void init() {
        celebrityActorsConverter.setMapper(mapper);
        Integer celebrityId = 1;
        filmography = new Filmography();
        Movie movie = new Movie();
        movie.setId(1);
        Cast cast = new Cast();
        cast.setCelebrityId(celebrityId);
        cast.setMovieId(1);
        movie.setCast(Arrays.asList(cast));
        filmography.setMovies(Arrays.asList(movie));
	}
	
	@Test
	public void testConvertTo() throws Exception{
        int totalSalary = 1000;
        Movies movies = new Movies();
        movies.setReleaseDate(LocalDate.now());
        movies.setId(1);

        Movies movies1 = new Movies();
        movies1.setReleaseDate(LocalDate.now());
        movies1.setId(2);

        MovieCast movieCast = new MovieCast();
        MovieCast movieCast1 = new MovieCast();
        movieCast.setId(1);
        movieCast.setMovies(movies);
        movieCast1.setId(2);
        movieCast1.setMovies(movies1);
        Set<MovieCast> movieCastSet = new HashSet<>();
        movieCastSet.add(movieCast);
        movieCastSet.add(movieCast1);
        actors.setMovieCast(movieCastSet);
        actors.setId(1);

        Movie movie = new Movie();
        movie.setGenres(Arrays.asList(Genre.ACTION));
        celebrity.setTotalSalary(new BigDecimal(1000));
        given(mapper.map(any(Movies.class), any())).willReturn(movie);
		Celebrity returnObject =celebrityActorsConverter.convertTo(actors, celebrity);

		assertNotNull(returnObject);
        assertEquals(500, returnObject.getAverageSalary().intValue());
	}
	

	@Test
	public void testConvertFrom() throws Exception{
        Actors returnObject = celebrityActorsConverter.convertFrom(null, null);
        assertNull(returnObject);
	}

    // @Test
    // public void testConvertFromWithEmptyMovieCast() throws Exception {
    // Integer celebrityId = 1;
    // Integer movieId = 1;
    // filmography = new Filmography();
    //
    // Movie movie = new Movie();
    // movie.setReleaseDate(LocalDate.now());
    // movie.setId(movieId);
    // Cast cast = new Cast();
    // cast.setCelebrityId(celebrityId);
    // cast.setMovieId(movieId);
    // Cast cast1 = new Cast();
    // cast1.setCelebrityId(celebrityId);
    // cast1.setMovieId(movieId + 1);
    // movie.setCast(Arrays.asList(cast, cast1));
    // filmography.setMovies(Arrays.asList(movie));
    // celebrity.setFilmography(filmography);
    // celebrity.setId(celebrityId);
    // celebrity.setGender(Gender.FEMALE);
    //
    // Movies movies = new Movies();
    // movies.setReleaseDate(LocalDate.now());
    // movies.setId(1);
    // Set<Movies> movieSet = new HashSet<>();
    // movieSet.add(movies);
    //
    // actors.setId(celebrityId);
    //
    // Actors returnObject = celebrityActorsConverter.convertFrom(celebrity, actors);
    // assertNotNull(returnObject);
    // }


}
