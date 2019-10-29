package gov.dhs.uscis.bdso.celebrity.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.service.DataLoadService;

@RunWith(MockitoJUnitRunner.class)
public class DatasourceApiImplTest {
    private MockMvc mockMvc;

    @InjectMocks
    private DatasourceApiImpl controller;
    
    @Mock
    private DataLoadService service;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoadDatasources() throws Exception {
        MvcResult result =
                mockMvc.perform(post("/api/v1/datasources/load").contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk()).andReturn();

        assertNotNull(result);
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    public void testAddCelebritiesException() throws Exception {
    
        doThrow(new BusinessException()).when(service).triggerLoad();
        MvcResult result =
        		 mockMvc.perform(post("/api/v1/datasources/load").contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isBadRequest()).andReturn();

        assertNotNull(result);
        assertEquals(400, result.getResponse().getStatus());
    }
    
}
