package com.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/home/**")
				.permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin(form -> form
			    .loginPage("/login")
			    .defaultSuccessUrl("/user/order", true)
			    .permitAll()
			    )
				.logout(logout -> {
					logout.logoutUrl("/logout")
					    .logoutSuccessUrl("/home");
					});
			 
				 
	}
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		/*
		UserDetails user =
				 User.builder()
					.username("ivana")
					.password(encoder.encode("ivana123"))
					.roles("ADM")
					.build();
		*/
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .passwordEncoder(encoder);
	     // .withUser(user);
	}
	
}