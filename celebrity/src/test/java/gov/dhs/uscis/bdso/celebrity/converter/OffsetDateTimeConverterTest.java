package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffsetDateTimeConverterTest {

	@InjectMocks
	OffsetDateTimeConverter offsetDateTimeConverter;
	
	@Test
	public void testConvertTo() throws Exception{
		LocalDateTime source = LocalDateTime.now();
		OffsetDateTime off = OffsetDateTime.now();
		OffsetDateTime returnTime = offsetDateTimeConverter.convertTo(source,off);
		assertNotNull(returnTime);
	}
	@Test
	public void testConvertToNull() throws Exception{
		OffsetDateTime returnTime = offsetDateTimeConverter.convertTo(null,null);
		assertNull(returnTime);
	}
	
	@Test
	public void testConvertFromNull() throws Exception{
		LocalDateTime returnTime = offsetDateTimeConverter.convertFrom(null,null);
		assertNull(returnTime);
	}
	
}
