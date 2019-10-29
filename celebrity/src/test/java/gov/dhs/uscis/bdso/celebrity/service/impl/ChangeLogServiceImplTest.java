package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.dozermapper.core.Mapper;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;
import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import gov.dhs.uscis.bdso.celebrity.repository.AuditLogRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLogServiceImplTest {

    @InjectMocks
    ChangeLogServiceImpl changeLogServiceImpl;


    @Mock
    private AuditLogRepository repository;

    @Mock
    private Mapper mapper;

    @Test
    public void testGetChangeLog() {
        List<AuditLog> auditLogs = new ArrayList<>();
        AuditLog auditLog = new AuditLog();
        ChangeLog log = new ChangeLog();
        auditLogs.add(auditLog);
        when(mapper.map(auditLog, ChangeLog.class)).thenReturn(log);
        when(repository.findAll()).thenReturn(auditLogs);
        List<ChangeLog> result = changeLogServiceImpl.getChangeLogs();
        assertNotNull(result);
    }

    @Test
    public void testGetChangeLogsById() {
        List<AuditLog> auditLogs = new ArrayList<>();
        AuditLog auditLog = new AuditLog();
        auditLogs.add(auditLog);
        when(repository.findByTableNameAndTableId(Mockito.anyString(), Mockito.anyString())).thenReturn(auditLogs);
        List<ChangeLog> result = changeLogServiceImpl.getChangeLogsById("1");
        assertNotNull(result);
    }


}
