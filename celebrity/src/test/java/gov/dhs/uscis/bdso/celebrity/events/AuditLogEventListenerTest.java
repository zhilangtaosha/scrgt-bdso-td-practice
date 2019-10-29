package gov.dhs.uscis.bdso.celebrity.events;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;
import gov.dhs.uscis.bdso.celebrity.domain.Audited;
import gov.dhs.uscis.bdso.celebrity.domain.AuditedEntity;
import gov.dhs.uscis.bdso.celebrity.repository.AuditLogRepository;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogEventListenerTest {
	
	@Mock
	private AuditLogRepository repository;
	
	@InjectMocks
	AuditLogEventListener auditLogEventListener;
	
	@Test
    public void testRequiresPostCommitHanding() throws Exception {
		EntityPersister persister = Mockito.mock(EntityPersister.class);
		boolean returnValue =auditLogEventListener.requiresPostCommitHanding(persister);
		assertEquals(false, returnValue);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
    public void testOnPostUpdate() throws Exception {
		PostUpdateEvent event = Mockito.mock(PostUpdateEvent.class);
		Audited audited = Mockito.mock(Audited.class);
		AuditedEntity entity = new Actors();
		when(event.getEntity()).thenReturn(entity);
		when(event.getEntity().getClass().getAnnotation(Audited.class)).thenReturn(audited);
		auditLogEventListener.onPostUpdate(event); 
	}
	
	@SuppressWarnings("rawtypes")
	@Test
    public void testOnPostInsert() throws Exception {
		PostInsertEvent insert = Mockito.mock(PostInsertEvent.class);
		Audited audited = Mockito.mock(Audited.class);
		AuditedEntity entity =Mockito.mock(AuditedEntity.class);
		when(insert.getEntity()).thenReturn(entity);
		when(insert.getEntity().getClass().getAnnotation(Audited.class)).thenReturn(audited);
		 auditLogEventListener.onPostInsert(insert);
		 
	}
	@SuppressWarnings("rawtypes")
	@Test
    public void testSaveLogs() throws Exception {
		Audited audited = Mockito.mock(Audited.class);
		AuditedEntity entity = Mockito.mock(AuditedEntity.class);
		List<AuditLog> logs = new ArrayList<AuditLog>();
		AuditLog log = new AuditLog();
		logs.add(log);
		when(entity.getChanges()).thenReturn(logs);
		 auditLogEventListener.saveLogs(audited, entity);
		
	}
 
	
}
