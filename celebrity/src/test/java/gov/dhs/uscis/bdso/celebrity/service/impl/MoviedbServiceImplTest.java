package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.MoviedbActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.MoviedbMovieRepository;

@RunWith(MockitoJUnitRunner.class)
public class MoviedbServiceImplTest {
	
	@Mock
	private MoviedbActorRepository moviedbActorRepository;
	
	@InjectMocks
	private MoviedbServiceImpl moviedbServiceImpl;
	
	@Mock
    private MoviedbMovieRepository moviedbMovieRepository;
	
	@Test
	public void testGetActorInfoByName() {
		List<MoviedbActorDocument> list = new ArrayList<>();
		MoviedbActorDocument actor = new MoviedbActorDocument();
		list.add(actor);
		when(moviedbActorRepository.findByName(Mockito.anyString())).thenReturn(list);
		List<MoviedbActorDocument> returnList = moviedbServiceImpl.getActorInfoByName("test");
		assertNotNull(returnList);
		assertEquals(1, returnList.size());
	}
	
	@Test
	public void testGetActorInfo() {
		 
		when(moviedbActorRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		MoviedbActorDocument returnWiki = moviedbServiceImpl.getActorInfo("test");
		assertNull(returnWiki);
 
	}
	
	@Test
	public void testGetAllCelebrities() {
		Iterable<MoviedbActorDocument> document = new HashSet<MoviedbActorDocument>();
		when(moviedbActorRepository.findAll()).thenReturn(document);
		Iterable<MoviedbActorDocument> returnWiki = moviedbServiceImpl.getAllCelebrities();
		assertNotNull(returnWiki);
 
	}
	
	@Test
	public void testAddDeleteActor() throws Exception {
		MoviedbActorDocument dto = new MoviedbActorDocument();
		dto.setId("1");
		when(moviedbActorRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dto));
		Mockito.doNothing().when(moviedbActorRepository).delete(dto);
		when(moviedbActorRepository.save(dto)).thenReturn(dto);
		MoviedbActorDocument returnWiki = moviedbServiceImpl.addActor(dto);
		moviedbServiceImpl.deleteActor("1");
		assertNotNull(returnWiki);
 
	}
	
	@Test
    public void testGetMovieInfoByTitle() {
		Mockito.when(moviedbMovieRepository.findByTitle(Mockito.anyString()))
						.thenReturn(new ArrayList<MoviedbMovieDocument>());
		
		moviedbServiceImpl.getMovieInfoByTitle("John");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(moviedbMovieRepository).findByTitle(captor.capture());
		assertEquals("John", captor.getValue());

    }
	
	@Test
    public void testGetMovieInfo() {
		MoviedbMovieDocument movie = new MoviedbMovieDocument();
		movie.setId("101");
		
		Mockito.when(moviedbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		MoviedbMovieDocument returnedMovie = moviedbServiceImpl.getMovieInfo("101");
		assertNotNull(returnedMovie);
		assertEquals("101", returnedMovie.getId());
		
		movie.setId(null);
		assertNull(moviedbServiceImpl.getMovieInfo("101"));
		
		movie = null;
		assertNull(moviedbServiceImpl.getMovieInfo("101"));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(moviedbMovieRepository, Mockito.times(3)).findById(captor.capture());
		assertEquals("101", captor.getValue());

    }
	
	
	@Test
	public void testGetAllMovies() {
		Mockito.when(moviedbMovieRepository.findAll()).thenReturn(new ArrayList<MoviedbMovieDocument>());
		
		assertNotNull(moviedbServiceImpl.getAllMovies());
		
		Mockito.verify(moviedbMovieRepository, Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void testAddMovie() throws BusinessException {
		MoviedbMovieDocument movie = new MoviedbMovieDocument();
		movie.setId("101");
		
		Mockito.when(moviedbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		Mockito.when(moviedbMovieRepository.save(Mockito.any(MoviedbMovieDocument.class)))
						.thenReturn(movie);
		
		MoviedbMovieDocument dto = new MoviedbMovieDocument();
		dto.setId("101");
		assertNotNull(moviedbServiceImpl.addMovie(dto));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(moviedbMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
	@Test
	public void testDeleteMovie() throws BusinessException {
		MoviedbMovieDocument movie = new MoviedbMovieDocument();
		movie.setId("101");
		
		Mockito.when(moviedbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		moviedbServiceImpl.deleteMovie("101");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(moviedbMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
 
}
