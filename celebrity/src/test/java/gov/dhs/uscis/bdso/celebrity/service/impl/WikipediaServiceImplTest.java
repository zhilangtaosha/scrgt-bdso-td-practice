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

import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.WikiActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.WikiMovieRepository;

@RunWith(MockitoJUnitRunner.class)
public class WikipediaServiceImplTest {
	
	
	@Mock
	private WikiActorRepository wikiActorRepository;
	
	@Mock
    private WikiMovieRepository wikiMovieRepository;

	@InjectMocks
	private WikipediaServiceImpl wikipediaServiceImpl;
	
	@Test
	public void testGetActorInfoByName() {
		List<WikiActorDocument> list = new ArrayList<>();
		
        WikiActorDocument actor = new WikiActorDocument();
		list.add(actor);
		
		when(wikiActorRepository.findByName(Mockito.anyString())).thenReturn(list);
		List<WikiActorDocument> returnList = wikipediaServiceImpl.getActorInfoByName("test");
		assertNotNull(returnList);
		assertEquals(1, returnList.size());
	}
	
	@Test
	public void testGetActorInfo() {
		 
		when(wikiActorRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		WikiActorDocument returnWiki = wikipediaServiceImpl.getActorInfo("test");
		assertNull(returnWiki);
 
	}
	
	@Test
	public void testGetAllCelebrities() {
		Iterable<WikiActorDocument> document = new HashSet<WikiActorDocument>();
		 
		when(wikiActorRepository.findAll()).thenReturn(document);
		Iterable<WikiActorDocument> returnWiki = wikipediaServiceImpl.getAllCelebrities();
		assertNotNull(returnWiki);
 
	}
	
	@Test
	public void testAddDeleteActor() throws Exception {
		WikiActorDocument dto = new WikiActorDocument();
		dto.setId("1");
		when(wikiActorRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dto));
		Mockito.doNothing().when(wikiActorRepository).delete(dto);
		when(wikiActorRepository.save(dto)).thenReturn(dto);
		WikiActorDocument returnWiki = wikipediaServiceImpl.addActor(dto);
		wikipediaServiceImpl.deleteActor("1");
		assertNotNull(returnWiki);
 
	}
	
	@Test
    public void testGetMovieInfo() {
		WikiMovieDocument movie = new WikiMovieDocument();
		movie.setMovieID("101");
		
		Mockito.when(wikiMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		WikiMovieDocument returnedMovie = wikipediaServiceImpl.getMovieInfo("101");
		assertNotNull(returnedMovie);
		assertEquals("101", returnedMovie.getMovieID());
		
		movie.setMovieID(null);
		assertNull(wikipediaServiceImpl.getMovieInfo("101"));
		
		movie = null;
		assertNull(wikipediaServiceImpl.getMovieInfo("101"));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(wikiMovieRepository, Mockito.times(3)).findById(captor.capture());
		assertEquals("101", captor.getValue());

    }
	
	@Test
	public void testGetAllMovies() {
		Mockito.when(wikiMovieRepository.findAll()).thenReturn(new ArrayList<WikiMovieDocument>());
		
		assertNotNull(wikipediaServiceImpl.getAllMovies());
		
		Mockito.verify(wikiMovieRepository, Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void testAddMovie() throws BusinessException {
		WikiMovieDocument movie = new WikiMovieDocument();
		movie.setMovieID("101");
		
		Mockito.when(wikiMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		Mockito.when(wikiMovieRepository.save(Mockito.any(WikiMovieDocument.class)))
						.thenReturn(movie);
		
		WikiMovieDocument dto = new WikiMovieDocument();
		dto.setMovieID("101");
		assertNotNull(wikipediaServiceImpl.addMovie(dto));
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(wikiMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}
	
	@Test
	public void testDeleteMovie() throws BusinessException {
		WikiMovieDocument movie = new WikiMovieDocument();
		movie.setMovieID("101");
		
		Mockito.when(wikiMovieRepository.findById(Mockito.anyString()))
						.thenReturn(Optional.of(movie));
		
		wikipediaServiceImpl.deleteMovie("101");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(wikiMovieRepository, Mockito.times(1)).findById(captor.capture());
		assertEquals("101", captor.getValue());
		
	}

}
