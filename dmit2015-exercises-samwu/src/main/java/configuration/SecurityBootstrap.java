package configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// The superclass AbstractSecurityWebApplicationInitializer registers the Spring Security filter.
public class SecurityBootstrap extends AbstractSecurityWebApplicationInitializer {

	public SecurityBootstrap() {
		super(SecurityConfiguration.class);
	}
	
}
