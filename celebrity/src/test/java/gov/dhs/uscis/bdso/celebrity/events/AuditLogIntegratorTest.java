package gov.dhs.uscis.bdso.celebrity.events;

import static org.mockito.Mockito.when;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogIntegratorTest {

	@Mock
	private AuditLogEventListener auditLogEventListener;
	
	@InjectMocks
	AuditLogIntegrator auditLogIntegrator;
	

	@Test
    public void testIntegrate() throws Exception {
		Metadata metadata = Mockito.mock(Metadata.class);
		SessionFactoryImplementor sessionFactory = Mockito.mock(SessionFactoryImplementor.class);
		SessionFactoryServiceRegistry serviceRegistry= Mockito.mock(SessionFactoryServiceRegistry.class);
		EventListenerRegistry eventListenerRegistry = Mockito.mock(EventListenerRegistry.class);
		
		when(serviceRegistry.getService(EventListenerRegistry.class)).thenReturn(eventListenerRegistry);
		auditLogIntegrator.integrate(metadata, sessionFactory, serviceRegistry);
		Mockito.verify(serviceRegistry).getService(EventListenerRegistry.class);
	}
	
	@Test
    public void testDisIntegrate() throws Exception {
		auditLogIntegrator.disintegrate(null, null);
	}
 
}
