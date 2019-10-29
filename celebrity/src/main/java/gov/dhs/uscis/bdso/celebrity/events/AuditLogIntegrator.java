package gov.dhs.uscis.bdso.celebrity.events;

import java.lang.invoke.MethodHandles;

import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class AuditLogIntegrator implements Integrator {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private AuditLogEventListener auditLogEventListener;

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventListenerRegistry.appendListeners(EventType.POST_INSERT, auditLogEventListener);
        eventListenerRegistry.appendListeners(EventType.POST_UPDATE, auditLogEventListener);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    	LOG.error("this method is not supported now");
    }
}
