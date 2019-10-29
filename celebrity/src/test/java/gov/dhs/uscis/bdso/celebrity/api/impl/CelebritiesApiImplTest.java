package gov.dhs.uscis.bdso.celebrity.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;

@RunWith(MockitoJUnitRunner.class)
public class CelebritiesApiImplTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @InjectMocks
    private CelebritiesApiImpl controller;
    
    @Mock
    private CelebrityService celebrityService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testFindCelebrities() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/celebrities").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(result);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testFindCelebrityById() throws Exception {
        Integer celebrityId = 12;
        MvcResult result = mockMvc
                .perform(get("/api/v1/celebrities/{celebrityId}", celebrityId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(result);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testAddCelebrity() throws Exception {
        Celebrity celeb = new Celebrity();
        celeb.setFirstName("John");
        celeb.setLastName("Doe");
        String json = mapper.writeValueAsString(celeb);

        MvcResult result =
                mockMvc.perform(post("/api/v1/celebrities").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                        .andExpect(status().isOk()).andReturn();

        assertNotNull(result);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testAddCelebrityException() throws Exception {
        Celebrity celeb = new Celebrity();
        celeb.setFirstName("John");
        celeb.setLastName("Doe");
        String json = mapper.writeValueAsString(celeb);

        Mockito.when(celebrityService.updateCelebrity(any(Celebrity.class))).thenThrow(new BusinessException());
        MvcResult result =
                mockMvc.perform(post("/api/v1/celebrities").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                        .andExpect(status().isBadRequest()).andReturn();

        assertNotNull(result);
        assertEquals(400, result.getResponse().getStatus());
    }

}
