package gov.dhs.uscis.bdso.celebrity.service;

import java.util.List;
import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;

public interface ChangeLogService {
    List<ChangeLog> getChangeLogsById(String id);
    
    List<ChangeLog> getChangeLogs();
}
