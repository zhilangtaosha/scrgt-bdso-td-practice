package gov.dhs.uscis.bdso.celebrity.api.elastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@RunWith(MockitoJUnitRunner.class)
public class CelebritiesElasticApiTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
	@InjectMocks
	private CelebritiesElasticApi controller;

	@Mock
	private ImdbService imdbService;

	@Mock
	private MoviedbService moviedbService;

	@Mock
	private WikipediaService wikipediaService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testGetImdbCelebrity() throws Exception {
		String id = "100";
		ImdbActorDocument actor = new ImdbActorDocument();
		Mockito.when(imdbService.getActorInfo(Mockito.anyString())).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/imdb/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(imdbService).getActorInfo("100");
	}
	
	@Test
	public void testGetImdbCelebrityException() throws Exception {
		String id = "100";
		Mockito.when(imdbService.getActorInfo(Mockito.anyString())).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/imdb/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testAddImdbCelebrity() throws Exception {
		String id = "100";
		ImdbActorDocument actor = new ImdbActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);
		 
		Mockito.when(imdbService.addActor(Mockito.any(ImdbActorDocument.class))).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/imdb/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(imdbService).addActor(actor);
	}
	
	@Test
	public void testAddImdbCelebrityException() throws Exception {
		String id = "100";
		ImdbActorDocument actor = new ImdbActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);

		Mockito.when(imdbService.addActor(Mockito.any(ImdbActorDocument.class))).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/imdb/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testGetMoviedbCelebrity() throws Exception {
		String id = "100";
		MoviedbActorDocument actor = new MoviedbActorDocument();
		Mockito.when(moviedbService.getActorInfo(Mockito.anyString())).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/moviedb/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(moviedbService).getActorInfo("100");
	}
	
	@Test
	public void testGetMoviedbCelebrityException() throws Exception {
		String id = "100";
		Mockito.when(moviedbService.getActorInfo(Mockito.anyString())).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/moviedb/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testAddMoviedbCelebrity() throws Exception {
		String id = "100";
		MoviedbActorDocument actor = new MoviedbActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);
		 
		Mockito.when(moviedbService.addActor(Mockito.any(MoviedbActorDocument.class))).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/moviedb/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(moviedbService).addActor(actor);
	}
	
	@Test
	public void testAddMoviedbCelebrityException() throws Exception {
		String id = "100";
		MoviedbActorDocument actor = new MoviedbActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);

		Mockito.when(moviedbService.addActor(Mockito.any(MoviedbActorDocument.class))).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/moviedb/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetWikiCelebrity() throws Exception {
		String id = "100";
		WikiActorDocument actor = new WikiActorDocument();
		Mockito.when(wikipediaService.getActorInfo(Mockito.anyString())).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/wikipedia/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(wikipediaService).getActorInfo("100");
	}
	
	@Test
	public void testGetWikiCelebrityException() throws Exception {
		String id = "100";
		Mockito.when(wikipediaService.getActorInfo(Mockito.anyString())).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/elastic/actor/wikipedia/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testAddWikiCelebrity() throws Exception {
		String id = "100";
		WikiActorDocument actor = new WikiActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);
		 
		Mockito.when(wikipediaService.addActor(Mockito.any(WikiActorDocument.class))).thenReturn(actor);
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/wikipedia/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(wikipediaService).addActor(actor);
	}
	
	@Test
	public void testAddwikiCelebrityException() throws Exception {
		String id = "100";
		WikiActorDocument actor = new WikiActorDocument();
		actor.setId(id);
		String json = mapper.writeValueAsString(actor);

		Mockito.when(wikipediaService.addActor(Mockito.any(WikiActorDocument.class))).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(post("/api/v1/celebrities/elastic/actor/wikipedia/add").
						contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}

}
