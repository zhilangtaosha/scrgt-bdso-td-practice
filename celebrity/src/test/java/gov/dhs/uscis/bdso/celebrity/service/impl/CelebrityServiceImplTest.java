package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.repository.ActorsRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MovieCastRepository;
import gov.dhs.uscis.bdso.celebrity.service.ImageService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityServiceImplTest {
    private static final String IMAGE_URL = "eyialh/hdiobk==";

    @Mock
    private ActorsRepository actorsRepository;

    @Mock
    private Mapper mapper;

    @Mock
    private S3Service s3Service;

    @Mock
    private ImageService imageService;

    @Mock
    private MovieCastRepository movieCastRepository;

	@InjectMocks
	private CelebrityServiceImpl celebrityServiceImpl;

    @Before
    public void init() {
    //    given(imageService.getImageAsBase64(anyString())).willReturn(BASE64_IMAGE);
        given(imageService.getImageAsUrl(anyString())).willReturn(IMAGE_URL);
        given(mapper.map(any(Actors.class), any())).willReturn(getCelebrity());
        given(mapper.map(any(Actors.class), any(), anyString())).willReturn(getCelebrity());
    }

	@Test
	public void testGetCelebrityInfoByName() {
		
		List<Actors> list = new ArrayList<>();
		
        Actors actor = getActor();
		list.add(actor);
		
		when(actorsRepository.findActorsByName(Mockito.anyString())).thenReturn(list);
        // when(mapper.map(actor, Celebrity.class)).thenReturn(value)
		List<Celebrity> celebrities = celebrityServiceImpl.getCelebrityInfoByName("John");
		
		assertEquals(1, celebrities.size());
		assertEquals(actor.getFirstName(), celebrities.get(0).getFirstName());

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(actorsRepository).findActorsByName(captor.capture());
		assertEquals("John", captor.getValue());
	}

    @Test
    public void testGetCelebrityInfo() {
        Actors actor = getActor();

        when(actorsRepository.findById(actor.getId())).thenReturn(Optional.of(actor));
        // when(mapper.map(actor, Celebrity.class)).thenReturn(value)
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfo(actor.getId());

        assertNotNull(celebrity);
        assertEquals(actor.getFirstName(), celebrity.getFirstName());

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(actorsRepository).findById(captor.capture());
        assertEquals(Integer.toString(1), captor.getValue().toString());
    }

    @Test
    public void testGetCelebrityInfoNotExist() {
        List<Actors> list = new ArrayList<>();

        Actors actor = getActor();
        list.add(actor);

        when(actorsRepository.findById(actor.getId())).thenReturn(Optional.empty());
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfo(actor.getId());

        assertNull(celebrity);
    }

    @Test
    public void testGetCelebrityInfoByImdbId() {
        Actors actor = getActor();

        when(actorsRepository.findActorsByImdbId(actor.getImdbId())).thenReturn(actor);
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfoByImdbId(actor.getImdbId());

        assertNotNull(celebrity);
        assertEquals(actor.getFirstName(), celebrity.getFirstName());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(actorsRepository).findActorsByImdbId(captor.capture());
        assertEquals(Integer.toString(1), captor.getValue());
    }
    
    @Test
    public void testGetCelebrityInfoByImdbIdNull() {
        when(actorsRepository.findActorsByImdbId(Mockito.anyString())).thenReturn(null);
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfoByImdbId("1");

        assertNull(celebrity);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(actorsRepository).findActorsByImdbId(captor.capture());
        assertEquals(Integer.toString(1), captor.getValue());
    }
    
    @Test
    public void testGetCelebrityInfoByMovdbId() {
        Actors actor = getActor();
        
        when(actorsRepository.findActorsByMovdbId(actor.getMovdbId())).thenReturn(actor);
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfoByMovdbId(actor.getMovdbId());

        assertNotNull(celebrity);
        assertEquals(actor.getFirstName(), celebrity.getFirstName());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(actorsRepository).findActorsByMovdbId(captor.capture());
        assertEquals(Integer.toString(1), captor.getValue());
    }
    
    @Test
    public void testGetCelebrityInfoByMovdbIdNull() {
        when(actorsRepository.findActorsByMovdbId(Mockito.anyString())).thenReturn(null);
        Celebrity celebrity = celebrityServiceImpl.getCelebrityInfoByMovdbId("1");

        assertNull(celebrity);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(actorsRepository).findActorsByMovdbId(captor.capture());
        assertEquals(Integer.toString(1), captor.getValue());
    }
    
    @Test
    public void testGetCelebritiesByActorIds() {
        String [] actorIds = {"John", "Jane"};
        Actors actor = getActor();
        
        when(actorsRepository.findActorsByMovdbId(Mockito.anyString())).thenReturn(actor);
        List<Celebrity> celebrity = celebrityServiceImpl.getCelebritiesByActorIds(actorIds);

        assertEquals(2, celebrity.size());
        verify(actorsRepository, times(2)).findActorsByMovdbId(anyString());
    }

    @Test
    public void testGetCelebritiesByMovieIds() {
        String[] movieIds = {"Avengers1", "Avengers2"};
        List<Actors> actors = Arrays.asList(getActor());

        when(actorsRepository.findActorsByMovieIds(anyList())).thenReturn(actors);
        List<Celebrity> celebrity = celebrityServiceImpl.getCelebritiesByMovieIds(movieIds);

        assertEquals(1, celebrity.size());
        verify(actorsRepository).findActorsByMovieIds(anyList());
    }

    @Test
    public void testGetCelebritiesByActorsAndMovies() {
        String[] actorIds = {"John", "Jane"};
        String[] movieIds = {"Avengers1", "Avengers2"};
        Actors actor = getActor();

        when(actorsRepository.findActorsByMovdbId(Mockito.anyString())).thenReturn(actor);
        JSONArray jsonArray = celebrityServiceImpl.getCelebritiesByActorsAndMovies(actorIds, movieIds);
    }

	@Test
	public void testGetAllCelebrities() {
		List<Actors> list = new ArrayList<>();
		
        Actors actor = getActor();
		list.add(actor);
		
		Actors actor2 = new Actors();
		actor2.setId(1);
		actor2.setFirstName("Jane");
		actor2.setLastName("Mitchell");
		actor2.setAvgRevenue(9999999L);
		actor2.setBoxOffice(4242343L);
		actor2.setNumMovies(20);
		list.add(actor2);
		
		when(actorsRepository.findAll()).thenReturn(list);
		List<Celebrity> celebrities = celebrityServiceImpl.getAllCelebrities();
		
		assertEquals(2, celebrities.size());

		verify(actorsRepository).findAll();
	}
	
	@Test
    public void testAddCelebriy() throws Exception {
		Celebrity dto = new Celebrity();
		dto.setFirstName("John");
		dto.setLastName("Doe");
        dto.setImage("dGhpcyBpcyBhbiBpbWFnZQo=");
		
		Actors actor = new Actors();
		actor.setFirstName("John");
		actor.setLastName("Doe");
		
		when(actorsRepository.save(Mockito.any(Actors.class))).thenReturn(actor);

        dto = celebrityServiceImpl.addCelebrity(dto);

		assertEquals("John", dto.getFirstName());
	}
	
    @Test
    public void testUpdateCelebriy() throws Exception {
        Celebrity dto = new Celebrity();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setImage("dGhpcyBpcyBhbiBpbWFnZQo=");

        Actors actor = new Actors();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");

        when(actorsRepository.save(Mockito.any(Actors.class))).thenReturn(actor);

        dto = celebrityServiceImpl.updateCelebrity(dto);

        assertEquals("John", dto.getFirstName());
    }

    @Test
    public void testAddCelebrities() throws Exception {
        Celebrity dto = new Celebrity();
        dto.setFirstName("John");
        dto.setLastName("Doe");

        List<Celebrity> celebrities = Arrays.asList(dto);

        Actors actor = new Actors();
        actor.setFirstName("John");
        actor.setLastName("Doe");

        celebrityServiceImpl.addCelebrities(celebrities);

        verify(actorsRepository).saveAll(anyList());
    }

    private Actors getActor() {
        Actors actor = new Actors();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setAvgRevenue(12345678L);
        actor.setBoxOffice(987654321L);
        actor.setNumMovies(10);
        actor.setImdbId("1");
        actor.setMovdbId("1");
        return actor;
    }

    private Celebrity getCelebrity() {
        Celebrity actor = new Celebrity();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
    //    actor.setImage(BASE64_IMAGE);
        actor.setImageUrl(IMAGE_URL);
        actor.setBiography("This is an accomplished actor");
        return actor;
    }
}
