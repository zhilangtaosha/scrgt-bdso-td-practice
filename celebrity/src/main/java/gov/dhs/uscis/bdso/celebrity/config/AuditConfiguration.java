package gov.dhs.uscis.bdso.celebrity.config;

import java.util.Collections;
import java.util.Map;
import javax.inject.Inject;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import gov.dhs.uscis.bdso.celebrity.events.AuditLogIntegrator;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration implements HibernatePropertiesCustomizer {
    @Inject
    private AuditLogIntegrator auditLogIntegrator;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SecurityAuditorAware();
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(auditLogIntegrator));
    }
}
