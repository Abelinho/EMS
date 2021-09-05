package com.luv2code.springboot.thymeleafdemo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
//@EnableResourceServer
//@EnableOAuth2Sso
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	// add a reference to our security data source
	
		@Autowired
		@Qualifier("securityDataSource")
		private DataSource securityDataSource;
			
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			// use jdbc authentication ... oh yeah!!!		
			auth.jdbcAuthentication().dataSource(securityDataSource);
			/*you could also use auth.userDetailsService() 
			 * or auth.authenticationProvider*/
			
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.authorizeRequests()
				.antMatchers("/employees/showForm*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/delete").hasRole("ADMIN")
				.antMatchers("/employees/**").hasRole("EMPLOYEE")
				.antMatchers("/resources/**").permitAll()
				.and()
				.formLogin()
					.loginPage("https://www.facebook.com/dialog/oauth")//this endpoint is called for login, try putting facebook auth url here
					.loginProcessingUrl("/authenticateTheUser")//Specify the URL to validate the credentials.
					.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");//<a href="/oauth2/authorization/facebook">Facebook</a>
			
		}//find out the url for facebook login page, so u can redirect to it
			
}
