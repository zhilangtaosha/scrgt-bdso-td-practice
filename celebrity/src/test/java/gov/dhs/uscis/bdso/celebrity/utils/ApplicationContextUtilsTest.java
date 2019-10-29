package gov.dhs.uscis.bdso.celebrity.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationContextUtilsTest {
	
	@Mock
	 private static ApplicationContext context;
	
	@InjectMocks
	ApplicationContextUtils applicationContextUtils;
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@Test
	public void testSetApplicationContext() {
		ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
		applicationContextUtils.setApplicationContext(applicationContext);
		applicationContextUtils.getBean(Mockito.any(Class.class));
	 
	}
	
	 

}
