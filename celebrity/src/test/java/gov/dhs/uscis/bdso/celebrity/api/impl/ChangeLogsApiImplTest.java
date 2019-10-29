package gov.dhs.uscis.bdso.celebrity.api.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import gov.dhs.uscis.bdso.celebrity.service.ChangeLogService;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLogsApiImplTest {
	
	
	@Mock
	private ChangeLogService changeLogService;
	
	@InjectMocks
	ChangeLogsApiImpl changeLogsApiImpl;

	@Test
    public void testGetChangeLogs() throws Exception {
    	List<ChangeLog> listLog = new ArrayList<ChangeLog>();
    	ChangeLog changeLog = new ChangeLog();
    	listLog.add(changeLog);
    	
    	when(changeLogService.getChangeLogs()).thenReturn(listLog);
    	ResponseEntity<List<ChangeLog>> result = changeLogsApiImpl.getChangeLogs();

        assertNotNull(result); 
    }
    
    @Test
    public void testFindCelebrityChangelogsById() throws Exception {
    	List<ChangeLog> listLog = new ArrayList<ChangeLog>();
    	ChangeLog changeLog = new ChangeLog();
    	listLog.add(changeLog);
    	
    	when(changeLogService.getChangeLogsById(Mockito.anyString())).thenReturn(listLog);
    	ResponseEntity<List<ChangeLog>> result = changeLogsApiImpl.findCelebrityChangelogsById(10);

        assertNotNull(result); 
	    }
	    
 

}
