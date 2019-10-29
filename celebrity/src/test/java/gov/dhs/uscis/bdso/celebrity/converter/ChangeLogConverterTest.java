package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.dhs.uscis.bdso.celebrity.model.Changes;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLogConverterTest {
	
	@InjectMocks
	ChangeLogConverter changeLogConverter;
	
	@Mock
	 ObjectMapper mapper;
	
	@Test
	public void testConvertTo() throws Exception{
		String source = "{\n" + 
				"    \"newValue\": \"true\"\n" + 
				"}";
		Changes returnObject = changeLogConverter.convertTo(source,null);
		assertNotNull(returnObject);
	}
	@Test(expected = AssertionError.class)
    public void testConvertToException() {
		String source = "test";
		Changes returnObject = changeLogConverter.convertTo(source,null);
		assertNotNull(returnObject);
    }
	@Test
	public void testConvertWithError() throws Exception{
	    Changes changes = new Changes();
	    changes.setOldValue("old");	     
        String source = new ObjectMapper().writeValueAsString(changes);
		assertNotNull(changeLogConverter.convertTo(source,null));
	}
	
	@Test
	public void testConvertFromNull() throws Exception{
		String returnObject = changeLogConverter.convertFrom(null,null);
		assertNull(returnObject);
	}

	
	
}
