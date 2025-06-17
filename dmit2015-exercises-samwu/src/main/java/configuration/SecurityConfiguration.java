package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("customer1")
						.password("Password2015")
						.authorities("CUSTOMER")
						.build(),
				User.withUsername("employee1")
						.password("Password2015")
						.authorities("EMPLOYEE")
						.build(),
				User.withUsername("admin1")
						.password("Password2015")
						.authorities("CUSTOMER", "EMPLOYEE", "ADMIN")
						.build()
		);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// ⚠️ For demonstration only. Use BCryptPasswordEncoder in production.
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		ActiveDirectoryLdapAuthenticationProvider ldapProvider =
				new ActiveDirectoryLdapAuthenticationProvider("dmit2015.ca", "ldap://2015dc.dmit2015.ca");

		return new ProviderManager(List.of(
				ldapProvider,
				new org.springframework.security.authentication.dao.DaoAuthenticationProvider() {{
					setUserDetailsService(userDetailsService);
					setPasswordEncoder(passwordEncoder());
				}}
		));
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/jakarta.faces.resource/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/index.xhtml", "/pages/*").permitAll()
						.requestMatchers("/pages/chinook/artists/createArtist.xhtml", "/pages/chinook/album/createAlbum.xhtml", "/pages/chinook/pos.xhtml")
						.hasAnyAuthority("EMPLOYEE", "Sales")
						.requestMatchers("/pages/chinook/artist/modifyArtist.xhtml", "/pages/chinook/album/modifyAlbum.xhtml")
						.hasAuthority("ADMIN")
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/pages/login.xhtml")
						.defaultSuccessUrl("/index.xhtml", true)
						.permitAll()
				)
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/index.xhtml")
						.permitAll()
				)
				.exceptionHandling(ex -> ex
						.accessDeniedPage("/pages/accessDenied.xhtml")
				);

		return http.build();
	}
}
