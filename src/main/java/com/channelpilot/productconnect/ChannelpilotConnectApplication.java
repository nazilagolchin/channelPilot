package com.channelpilot.productconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.channelpilot.productconnect.security.JWTAuthorizationFilter;

@SpringBootApplication
@EnableJpaRepositories("com.channelpilot.productconnect")
public class ChannelpilotConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChannelpilotConnectApplication.class, args);
	}

	/**
	 * @author Nazila Golchin configuration security with Spring, configure the
	 *         authorization and permitAll for swagger and h2 so all requests are
	 *         allowed on these particular path
	 */
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user").permitAll()
					.antMatchers("/swagger-resources/**").permitAll().antMatchers("/swagger-ui.html").permitAll()
					.antMatchers("/configuration/security").permitAll().antMatchers("/configuration/ui").permitAll().antMatchers("/configuration/**").permitAll()
					.antMatchers("/webjars/**").permitAll().antMatchers("/v2/api-docs").permitAll()
					.antMatchers("/swagger-ui/**").permitAll()
					.antMatchers("/h2-console/**").permitAll().antMatchers("/h2/**").permitAll().antMatchers("/")
					.permitAll().antMatchers("/console/**").permitAll().antMatchers("/error").permitAll()
					.antMatchers("/error/**").permitAll().anyRequest().authenticated();
			//http.headers().frameOptions().disable();
		}
	}
}
