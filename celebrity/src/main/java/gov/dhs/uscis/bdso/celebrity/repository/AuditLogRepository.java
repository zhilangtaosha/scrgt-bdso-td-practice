package gov.dhs.uscis.bdso.celebrity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog> findByTableNameAndTableId(String tableName, String tableId);
}