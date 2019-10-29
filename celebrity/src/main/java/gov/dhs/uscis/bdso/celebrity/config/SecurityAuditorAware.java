package gov.dhs.uscis.bdso.celebrity.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getUserId());
    }

    private String getUserId() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return "system";
        }

        if (auth instanceof OAuth2AuthenticationToken) {
            return ((OAuth2AuthenticationToken) auth).getName();
        } else if (auth.getPrincipal() != null) {
            return auth.getPrincipal().toString();
        } else {
            return "system";
        }
    }

}
