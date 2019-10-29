package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.ImdbActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.ImdbMovieRepository;

@RunWith(MockitoJUnitRunner.class)
public class ImdbServiceImplTest {

	@InjectMocks
    private ImdbServiceImpl imdbService;
	
	@Mock
    private ImdbActorRepository imdbActorRepository;
	
	@Mock
    private ImdbMovieRepository imdbMovieRepository;
	
	@Test
    public void testGetActorInfoByName() {
		Mockito.when(imdbActorRepository.findByBirthName(Mockito.anyString()))
						.thenReturn(new ArrayList<ImdbActorDocument>());
		
		imdbService.getActorInfoByName("John");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbActorRepository).findByBirthName(captor.capture());
		assertEquals("John", captor.getValue());

    }
	
	@Test
    public void testGetActorInfo() {
		ImdbActorDocument actor = new ImdbActorDocument();
		actor.setId("101");
		
		Mockito.when(imdbActorRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(actor));
		
		ImdbActorDocument returnedActor = imdbService.getActorInfo("101");
		assertNotNull(returnedActor);
		assertEquals("101", returnedActor.getId());
		
		actor.setId(null);
		assertNull(imdbService.getActorInfo("101"));
		
		actor = null;
		assertNull(imdbService.getActorInfo("101"));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbActorRepository, Mockito.times(3)).findById(captor.capture());
		assertEquals("101", captor.getValue());

    }
	
	@Test
	public void testGetAllCelebrities() {
		Mockito.when(imdbActorRepository.findAll()).thenReturn(new ArrayList<ImdbActorDocument>());
		
		assertNotNull(imdbService.getAllCelebrities());
		
		Mockito.verify(imdbActorRepository, Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void testAddActor() throws BusinessException {
		ImdbActorDocument actor = new ImdbActorDocument();
		actor.setId("101");
		
		Mockito.when(imdbActorRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(actor));
		Mockito.when(imdbActorRepository.save(Mockito.any(ImdbActorDocument.class)))
						.thenReturn(actor);
		
		ImdbActorDocument dto = new ImdbActorDocument();
		dto.setId("101");
		assertNotNull(imdbService.addActor(dto));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbActorRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
	@Test
	public void testDeleteActor() throws BusinessException {
		ImdbActorDocument actor = new ImdbActorDocument();
		actor.setId("101");
		
		Mockito.when(imdbActorRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(actor));
		
		imdbService.deleteActor("101");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbActorRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
	@Test
    public void testGetMovieInfoByTitle() {
		Mockito.when(imdbMovieRepository.findByTitle(Mockito.anyString()))
						.thenReturn(new ArrayList<ImdbMovieDocument>());
		
		imdbService.getMovieInfoByTitle("John");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbMovieRepository).findByTitle(captor.capture());
		assertEquals("John", captor.getValue());

    }
	
	@Test
    public void testGetMovieInfo() {
		ImdbMovieDocument movie = new ImdbMovieDocument();
		movie.setId("101");
		
		Mockito.when(imdbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		ImdbMovieDocument returnedMovie = imdbService.getMovieInfo("101");
		assertNotNull(returnedMovie);
		assertEquals("101", returnedMovie.getId());
		
		movie.setId(null);
		assertNull(imdbService.getMovieInfo("101"));
		
		movie = null;
		assertNull(imdbService.getMovieInfo("101"));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbMovieRepository, Mockito.times(3)).findById(captor.capture());
		assertEquals("101", captor.getValue());

    }
	
	@Test
	public void testGetAllMovies() {
		Mockito.when(imdbMovieRepository.findAll()).thenReturn(new ArrayList<ImdbMovieDocument>());
		
		assertNotNull(imdbService.getAllMovies());
		
		Mockito.verify(imdbMovieRepository, Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void testAddMovie() throws BusinessException {
		ImdbMovieDocument movie = new ImdbMovieDocument();
		movie.setId("101");
		
		Mockito.when(imdbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		Mockito.when(imdbMovieRepository.save(Mockito.any(ImdbMovieDocument.class)))
						.thenReturn(movie);
		
		ImdbMovieDocument dto = new ImdbMovieDocument();
		dto.setId("101");
		assertNotNull(imdbService.addMovie(dto));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
	@Test
	public void testDeleteMovie() throws BusinessException {
		ImdbMovieDocument movie = new ImdbMovieDocument();
		movie.setId("101");
		
		Mockito.when(imdbMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		imdbService.deleteMovie("101");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(imdbMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
}
