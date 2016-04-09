package configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// Registering Spring Security with the WAR
public class SecurityWebApplicationInitializer 
	extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}
}
