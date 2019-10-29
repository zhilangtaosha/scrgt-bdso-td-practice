package gov.dhs.uscis.bdso.celebrity.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
    private static final String ZONE_EST = "America/New_York";
    private static final ZoneId ZONE_ID = ZoneId.of(ZONE_EST);

    @CreatedDate
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(ZONE_ID);

    @CreatedBy
    @NotNull
    @Column(nullable = false)
    private String createdBy;
    
    @LastModifiedDate
    private LocalDateTime updatedDate = LocalDateTime.now(ZONE_ID);

    @LastModifiedBy
    private String updatedBy;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
