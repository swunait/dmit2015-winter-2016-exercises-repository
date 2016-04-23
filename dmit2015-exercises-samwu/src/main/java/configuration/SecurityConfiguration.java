package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
					.withUser("customer1")
					.password("Password2015")
					.authorities("CUSTOMER")
				.and()
					.withUser("employee1")
					.password("Password2015")
					.authorities("EMPLOYEE")
				.and()
					.withUser("admin1")
					.password("Password2015")
					.authorities("CUSTOMER","EMPLOYEE","ADMIN");
				
		builder.authenticationProvider(
				new ActiveDirectoryLdapAuthenticationProvider("dmit2015.ca", "ldap://2015dc.dmit2015.ca")
		);
	}
	
	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/resources/**","/javax.faces.resource/**");
	}

	@Override
	public void configure(HttpSecurity security) throws Exception {
		security
				.authorizeRequests()
					.antMatchers("/index.xhtml","/pages/*").permitAll()
					.antMatchers("/pages/chinook/artists/createArtist.xhtml","/pages/chinook/album/createAlbum.xhtml","/pages/chinook/pos.xhtml").hasAnyAuthority("EMPLOYEE","Sales")
					.antMatchers("/pages/chinook/artist/modifyArtist.xhtml","/pages/chinook/album/modifyAlbum.xhtml").hasAuthority("ADMIN")
					.anyRequest().authenticated()
				.and().formLogin()
					.loginPage("/pages/login.xhtml")
					.defaultSuccessUrl("/index.xhtml")
					.permitAll()
				.and().logout()
					.logoutUrl("/logout").logoutSuccessUrl("/index.xhtml")
					.permitAll()
				.and()
					.exceptionHandling().accessDeniedPage("/pages/accessDenied.xhtml");
	}
}
