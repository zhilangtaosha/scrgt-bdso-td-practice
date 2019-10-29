package gov.dhs.uscis.bdso.celebrity.api.elastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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

import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;
import gov.dhs.uscis.bdso.celebrity.service.QueryService;

@RunWith(MockitoJUnitRunner.class)
public class CelebritiesQueryApiTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
	@InjectMocks
	private CelebritiesQueryApi controller;

    @Mock
    private QueryService dbQueryService;
    
    @Mock
    private CelebrityService celebrityService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testGetFilmsByGenderData() throws Exception {
		JSONArray data = new JSONArray();
		
		Mockito.when(dbQueryService.getFilmsByGender()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/filmsByGender").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getFilmsByGender();
	}
	
	@Test
	public void testGetFilmsByGenderDataException() throws Exception {
		Mockito.when(dbQueryService.getFilmsByGender()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/filmsByGender").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getFilmsByGender();
	}

	@Test
	public void testGetSalaryByGenderData() throws Exception {
		JSONArray data = new JSONArray();
		
		Mockito.when(dbQueryService.getSalaryByGender()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/salaryByGender").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getSalaryByGender();
	}
	
	@Test
	public void testGetSalaryByGenderDataException() throws Exception {
		Mockito.when(dbQueryService.getSalaryByGender()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/salaryByGender").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getSalaryByGender();
	}
	
	@Test
	public void testGetAnnualBoxOfficeTotals() throws Exception {
		JSONArray data = new JSONArray();
		
		Mockito.when(dbQueryService.getAnnualBoxOfficeTotals()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/annualTotals").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getAnnualBoxOfficeTotals();
	}
	
	@Test
	public void testGetAnnualBoxOfficeTotalsException() throws Exception {
		Mockito.when(dbQueryService.getAnnualBoxOfficeTotals()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/annualTotals").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getAnnualBoxOfficeTotals();
	}
	
	@Test
	public void testGetFilmCountsByYear() throws Exception {
		JSONArray data = new JSONArray();
		
		Mockito.when(dbQueryService.getFilmCountsByYear()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/filmsByYear").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getFilmCountsByYear();
	}
	
	@Test
	public void testGetFilmCountsByYearException() throws Exception {
		Mockito.when(dbQueryService.getFilmCountsByYear()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/filmsByYear").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getFilmCountsByYear();
	}
	
	@Test
	public void testGetSalaryCountsByYear() throws Exception {
		JSONArray data = new JSONArray();
		
		Mockito.when(dbQueryService.getCelebritySalaryByYear()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/salaryByYear").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getCelebritySalaryByYear();
	}
	
	@Test
	public void testGetSalaryCountsByYearException() throws Exception {
		Mockito.when(dbQueryService.getCelebritySalaryByYear()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/salaryByYear").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getCelebritySalaryByYear();
	}
	
	@Test
	public void testGetSummaryCounts() throws Exception {
		JSONObject data = new JSONObject();
		
		Mockito.when(dbQueryService.getSummaryCounts()).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/summaryCounts").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getSummaryCounts();
	}
	
	@Test
	public void testGetSummaryCountsException() throws Exception {
		Mockito.when(dbQueryService.getSummaryCounts()).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/summaryCounts").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
		Mockito.verify(dbQueryService).getSummaryCounts();
	}
	
	@Test
	public void testGetCelebrities() throws Exception {
		List<Celebrity> data = new ArrayList<>();
		String[] actorIds = new String[] {"1", "2"};
		
		Mockito.when(celebrityService.getCelebritiesByActorIds(actorIds)).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/byActorIds")
						.param("actors", actorIds)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(celebrityService).getCelebritiesByActorIds(actorIds);
	}
	
	@Test
	public void testGetCelebritiesException() throws Exception {
		String[] actorIds = new String[] {"1", "2"};
		
		Mockito.when(celebrityService.getCelebritiesByActorIds(actorIds)).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/byActorIds")
						.param("actors", actorIds)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetCelebritiesByIds() throws Exception {
		JSONArray data = new JSONArray();
		String[] actorIds = new String[] {"1", "2"};
		String[] movieIds = new String[] {"101", "102"};
		
		Mockito.when(celebrityService.getCelebritiesByActorsAndMovies(actorIds, movieIds)).thenReturn(data);
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/byIds")
						.param("actors", actorIds)
						.param("movies", movieIds)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);
		assertEquals(200, result.getResponse().getStatus());
		Mockito.verify(celebrityService).getCelebritiesByActorsAndMovies(actorIds, movieIds);
	}
	
	@Test
	public void testGetCelebritiesByIdsException() throws Exception {
		String[] actorIds = new String[] {"1", "2"};
		String[] movieIds = new String[] {"101", "102"};
		
		Mockito.when(celebrityService.getCelebritiesByActorsAndMovies(actorIds, movieIds)).thenThrow(new RuntimeException());
		MvcResult result = mockMvc
				.perform(get("/api/v1/celebrities/query/byIds")
						.param("actors", actorIds)
						.param("movies", movieIds)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn();

		assertNotNull(result);
		assertEquals(400, result.getResponse().getStatus());
	}
}
