package configuration;

import org.jboss.arquillian.protocol.servlet.arq514hack.descriptors.impl.web.WebAppDescriptorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity	// register Spring Security in the application
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("customer1")
        	.password("Password2015").roles("CUSTOMER");
        
        auth.inMemoryAuthentication().withUser("employee1")
    	.password("Password2015").roles("EMPLOYEE");
        
        auth.inMemoryAuthentication().withUser("admin")
    	.password("Password2015").roles("ADMIN");
        
        auth.inMemoryAuthentication().withUser("developer")
    	.password("Password2015").roles("ADMIN","EMPLOYEE");        
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/pages/chinook/pos**")
			.authorizeRequests()
			.anyRequest()
			.hasRole("EMPLOYEE")
			.and()
			.httpBasic();
	}
}
