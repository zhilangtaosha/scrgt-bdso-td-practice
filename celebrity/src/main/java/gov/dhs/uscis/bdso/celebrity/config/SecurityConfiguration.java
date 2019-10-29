package gov.dhs.uscis.bdso.celebrity.config;

import java.lang.invoke.MethodHandles;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@EnableWebSecurity
@EnableResourceServer
@ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private ResourceServerProperties resourceServerProperties;

    @Value("${security.enabled}")
    private boolean securityEnabled;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceServerProperties.getResourceId());
    }
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        LOG.debug("Security Enabled - {}", securityEnabled);
		
		http.authorizeRequests()
    		.antMatchers(
    		     "/actuator/**",
    		     "/v2/api-docs", 
				 "/swagger-resources/configuration/ui", 
				 "/swagger-resources", 
				 "/swagger-resources/configuration/security", 
				 "/swagger-ui.html", 
                 "/webjars/**",
                 "/api/v1/datasources/load")
                .permitAll()
    		.and()
                .authorizeRequests().anyRequest().authenticated();

    }
}
