package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Movie;
import gov.dhs.uscis.bdso.celebrity.repository.MoviesRepository;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

	@Mock
	private MoviesRepository moviesRepository;

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

    @Mock
    private Mapper mapper;

    @Before
    public void init() {
        given(mapper.map(any(Movies.class), any())).willReturn(createDto());
    }

	@Test
	public void testGetMovieInfoByTitle() {
		Movies movie = new Movies();
		movie.setTitle("Shawshank Redemption");

		when(moviesRepository.findByTitle(Mockito.anyString())).thenReturn(movie);
		assertNotNull(movieServiceImpl.getMovieInfoByTitle("Shawshank Redemption"));

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(moviesRepository).findByTitle(captor.capture());
		assertEquals("Shawshank Redemption", captor.getValue());
	}

	@Test
	public void testGetMovieInfoById() {
		Movies movie = new Movies();
		movie.setId(1);
		movie.setTitle("Shawshank Redemption");

		when(moviesRepository.findById(1)).thenReturn(Optional.of(movie));
		assertNotNull(movieServiceImpl.getMovieInfoById(1));

		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		verify(moviesRepository).findById(captor.capture());
		assertEquals(Integer.valueOf(1), captor.getValue());
	}
	
	@Test
	public void testGetMovieInfoByImdbId() {
		Movies movie = new Movies();
		movie.setId(1);
		movie.setTitle("Shawshank Redemption");
		movie.setImdbId("1");

		when(moviesRepository.findByImdbId(Mockito.anyString())).thenReturn(movie);
		assertNotNull(movieServiceImpl.getMovieInfoByImdbId("1"));

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(moviesRepository).findByImdbId(captor.capture());
		assertEquals("1", captor.getValue());
	}
	
	@Test
	public void testGetMovieInfoByMovdbId() {
		Movies movie = new Movies();
		movie.setId(1);
		movie.setTitle("Shawshank Redemption");
		movie.setMovdbId("1");

		when(moviesRepository.findByMovdbId(Mockito.anyString())).thenReturn(movie);
		assertNotNull(movieServiceImpl.getMovieInfoByMovdbId("1"));

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(moviesRepository).findByMovdbId(captor.capture());
		assertEquals("1", captor.getValue());
	}
	
	@Test
	public void testGetAllMoviesInfoByTitle() {
		Movies movie = new Movies();
		movie.setTitle("Shawshank Redemption");
		List<Movies> list = new ArrayList<>();
		list.add(movie);

		when(moviesRepository.findMoviesByTitle(Mockito.anyString())).thenReturn(list);
		assertNotNull(movieServiceImpl.getAllMoviesInfoByTitle("Shawshank"));

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(moviesRepository).findMoviesByTitle(captor.capture());
		assertEquals("Shawshank", captor.getValue());
	}
	
    @Test
    public void testGetAllMovies() {
        Movies movie = new Movies();
        movie.setTitle("Shawshank Redemption");

        Set<MovieGenres> genres = new HashSet<>();
        MovieGenres genre = new MovieGenres();
        genre.setGenre("Action");
        genres.add(genre);
        movie.setGenres(genres);

        List<Movies> list = new ArrayList<>();
        list.add(movie);

        when(moviesRepository.findAll()).thenReturn(list);
        assertNotNull(movieServiceImpl.getAllMovies());

        verify(moviesRepository).findAll();
    }

	@Test
	public void testAddMovie() {
		Movie dto = createDto();
		
		when(moviesRepository.save(Mockito.any(Movies.class))).thenReturn(createMovie());
		
		try {
			dto = movieServiceImpl.addMovie(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Shawshank Redemption", dto.getTitle());
	}
	
	@Test
	public void testUpdateMovie() {
		Movie dto = createDto();
		dto.setId(1);
		
		Movies movie = createMovie();
		movie.setId(1);
		
		when(moviesRepository.findById(1)).thenReturn(Optional.of(movie));
		when(moviesRepository.save(Mockito.any(Movies.class))).thenReturn(movie);
		
		try {
			dto = movieServiceImpl.updateMovie(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Shawshank Redemption", dto.getTitle());
	}
	
	private Movie createDto() {
		Movie dto = new Movie();
		dto.setTitle("Shawshank Redemption");
		dto.setId(1);
		dto.setOverview("overview");
		dto.setTagline("test plot");
		
//		List<Cast> actors = new ArrayList<>();
//		Cast castRecord = new Cast();
//		castRecord.setRoleName("My Role");
//		castRecord.setCelebrityId(1);
//		actors.add(castRecord);
//		dto.setCast(actors);
//		
//		List<Genre> genres = new ArrayList<>();
//		genres.add(Genre.HORROR);
//		dto.setGenres(genres);
//		
//		List<Company> companies = new ArrayList<>();
//		Company company = new Company();
//		company.setCompanyName("ABC");
//		companies.add(company);
//		dto.setCompanies(companies);
		
		return dto;
	}
	
	private Movies createMovie() {
		Movies movie = new Movies();
		movie.setTitle("Shawshank Redemption");
		movie.setId(1);
		movie.setOverview("overview");
		movie.setTagline("test plot");
		
//		List<MovieCast> actors = new ArrayList<>();
//		MovieCast castRecord = new MovieCast();
//		castRecord.setRoleName("My Role");
//		castRecord.set
//		actors.add(castRecord);
//		dto.setCast(actors);
//		
//		List<Genre> genres = new ArrayList<>();
//		genres.add(Genre.HORROR);
//		dto.setGenres(genres);
//		
//		List<Company> companies = new ArrayList<>();
//		Company company = new Company();
//		company.setCompanyName("ABC");
//		companies.add(company);
//		dto.setCompanies(companies);
		
		return movie;
	}
}
