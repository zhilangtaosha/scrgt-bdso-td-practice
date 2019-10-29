package gov.dhs.uscis.bdso.celebrity.domain;


import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.dhs.uscis.bdso.celebrity.model.Changes;
import gov.dhs.uscis.bdso.celebrity.utils.ApplicationContextUtils;

@MappedSuperclass
public abstract class AuditedEntity<T> extends AuditEntity {
    private static ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Transient
    @JsonIgnore
    private T originalEntity;

    abstract Serializable getEntityId();

    @SuppressWarnings("unchecked")
    public List<AuditLog> getChanges() {
        initMapper();
		Class<T> c = (Class<T>) this.getClass();
        Field[] fields = c.getDeclaredFields();
        List<AuditLog> auditLogs = new ArrayList<>();

        for (Field field : fields) {
            try {
                if (isAuditableField(field)) {
                    String newValue = getValue(field, this);
                    String oldValue = getValue(field, originalEntity);
                    
                    if (oldValue != null && newValue != null && !oldValue.equals(newValue)
                            || StringUtils.isNotEmpty(oldValue) && StringUtils.isEmpty(newValue)
                            || StringUtils.isEmpty(oldValue) && StringUtils.isNotEmpty(newValue)) {
                        AuditLog log = mapAuditLog(field.getName(), oldValue, newValue);
                        auditLogs.add(log);
                    }
                }
            } catch (IllegalArgumentException e) {
            	logger.error("illegal Argument exception at getChanges method", e);
            } catch (IllegalAccessException e) {
            	logger.error("illegal access exception at getChanges method", e);
            }
        }

        return auditLogs;
    }

    private boolean isAuditableField(Field field) {
        return !field.getName().equals("id") && (String.class.isAssignableFrom(field.getType())
                || LocalDateTime.class.isAssignableFrom(field.getType())
                || Integer.class.isAssignableFrom(field.getType()) || Long.class.isAssignableFrom(field.getType()));
    }

    private AuditLog mapAuditLog(String fieldName, String oldValue, String newValue) {
        AuditLog log = null;

        try {
            StringWriter sw = new StringWriter();

            log = new AuditLog();
            Changes changes = new Changes();
            log.setTableName(this.getClass().getSimpleName());
            log.setTableId(this.getEntityId().toString());
            log.setFieldName(fieldName);
            log.setChangeDate(this.getUpdatedDate());
            log.setChangedBy(this.getUpdatedBy());
            changes.oldValue(oldValue);
            changes.newValue(newValue);
            mapper.writerWithDefaultPrettyPrinter().writeValue(sw, changes);
            log.setChanges(sw.toString());
        } catch (IOException e) {
        	logger.error("IO exception at mapAuditLog method", e);
        }

        return log;
    }

    private String getValue(Field field, Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }

        Object o = null;
        field.setAccessible(true);
        if (String.class.isAssignableFrom(field.getType())) {
            o = field.get(obj);
        } else {
            o = field.get(obj);

            if (o != null) {
                o = o.toString();
            }
        }

        return o != null ? (String) o : null;
    }

    @PostLoad
    public void onLoad() {
        try {
           initMapper();

            StringWriter sw = new StringWriter();
            mapper.writerWithDefaultPrettyPrinter().writeValue(sw, this);
            String json = sw.toString();
            this.originalEntity = mapper.reader().forType(this.getClass()).readValue(json);
        } catch (IOException e) {
        	logger.error("IO exception at mapAuditLog method", e);
        }
    }

    private static void initMapper() {
        if (mapper == null) {
            mapper = ApplicationContextUtils.getBean(ObjectMapper.class);
        }
    }
}
