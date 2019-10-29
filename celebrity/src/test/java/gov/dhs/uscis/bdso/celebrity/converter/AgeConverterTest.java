package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AgeConverterTest {
    private static final ZoneId ZONE_ID = ZoneId.of("America/New_York");

	@InjectMocks
    AgeConverter ageConverter;
	
	@Test
	public void testConvertTo() throws Exception{
        Integer age = 30;
        LocalDate dob = LocalDate.now(ZONE_ID).minus(30, ChronoUnit.YEARS);

        Integer retVal = ageConverter.convertTo(dob, null);
        assertNotNull(retVal);
        assertEquals(age, retVal);
	}

    @Test
    public void testConvertToNull() throws Exception {
        Integer retVal = ageConverter.convertTo(null, null);
        assertNull(retVal);
    }

	@Test
    public void testConvertFrom() throws Exception {
        Integer age = 30;
        LocalDate dob = LocalDate.now(ZONE_ID).minus(30, ChronoUnit.YEARS);

        LocalDate retVal = ageConverter.convertFrom(age, null);
        assertNotNull(retVal);
        assertEquals(dob, retVal);
	}

    @Test
    public void testConvertFromNull() throws Exception {
        LocalDate retVal = ageConverter.convertFrom(null, null);
        assertNull(retVal);
    }
}
