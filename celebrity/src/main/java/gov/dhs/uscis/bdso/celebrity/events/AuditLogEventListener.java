package gov.dhs.uscis.bdso.celebrity.events;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.context.annotation.Lazy;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;
import gov.dhs.uscis.bdso.celebrity.domain.Audited;
import gov.dhs.uscis.bdso.celebrity.domain.AuditedEntity;
import gov.dhs.uscis.bdso.celebrity.repository.AuditLogRepository;

@Named
public class AuditLogEventListener implements PostUpdateEventListener, PostInsertEventListener {
    private static final long serialVersionUID = 1901948653362352365L;

    @Lazy
    @Inject
    private AuditLogRepository repository;

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        saveLogs(event.getEntity().getClass().getAnnotation(Audited.class), event.getEntity());
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        saveLogs(event.getEntity().getClass().getAnnotation(Audited.class), event.getEntity());
    }

    protected void saveLogs(Audited audited, Object obj) {
        if (audited != null) {
            AuditedEntity entity = (AuditedEntity) obj;
            List<AuditLog> logs = entity.getChanges();

            if (logs != null && !logs.isEmpty()) {
                repository.saveAll(logs);
            }
        }
    }
}
