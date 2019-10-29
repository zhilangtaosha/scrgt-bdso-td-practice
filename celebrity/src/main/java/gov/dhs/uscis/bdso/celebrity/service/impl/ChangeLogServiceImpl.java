package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;
import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import gov.dhs.uscis.bdso.celebrity.repository.AuditLogRepository;
import gov.dhs.uscis.bdso.celebrity.service.ChangeLogService;

@Named
public class ChangeLogServiceImpl implements ChangeLogService {
    @Inject
    private AuditLogRepository repository;

    @Inject
    private Mapper mapper;

    @Override
    public List<ChangeLog> getChangeLogs() {
        List<AuditLog> auditLogs = repository.findAll();
        return mapChangeLog(auditLogs);
    }

    @Override
    public List<ChangeLog> getChangeLogsById(String id) {
        List<AuditLog> auditLogs = repository.findByTableNameAndTableId("Actors", id);
        return mapChangeLog(auditLogs);
    }

    private List<ChangeLog> mapChangeLog(List<AuditLog> auditLogs) {
        List<ChangeLog> logs = new ArrayList<>();

        for (AuditLog auditLog : auditLogs) {
            ChangeLog log = mapper.map(auditLog, ChangeLog.class);

            if (log != null) {
                logs.add(log);
            }
        }

        return logs;
    }
}
