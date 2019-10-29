package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.DeserializationContext;

@RunWith(MockitoJUnitRunner.class)
public class CamelCaseKeySerializerTest {

	
	@InjectMocks
	private CamelCaseKeySerializer camelCaseKeySerializer;
	
	 
	
	DeserializationContext ctxt = Mockito.mock(DeserializationContext.class);
	
	@Test
	public void testDeserializeKeyWithContainsKey() throws Exception{
		
		String key = "_actorID";
		Object returnObject = camelCaseKeySerializer.deserializeKey(key, ctxt);
		assertNotNull(returnObject);
		assertEquals(key, returnObject);
		
	}
	
	@Test
	public void testDeserializeKey() throws Exception{
		
		String key = "_actor123";
		Object returnObject = camelCaseKeySerializer.deserializeKey(key, ctxt);
		assertNotNull(returnObject);
		assertEquals("actor123", returnObject);
	}
		
	@Test
	public void testDeserializeKeyWithMoreTokens() throws Exception{
		
		String key = "_actor123_actor2356";
		Object returnObject = camelCaseKeySerializer.deserializeKey(key, ctxt);
		assertNotNull(returnObject);
		assertEquals("actor123Actor2356", returnObject);
	}
	
	
}
