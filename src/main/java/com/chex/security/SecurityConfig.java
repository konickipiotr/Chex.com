package com.chex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

	@Autowired
	private CustomAuthenticationHandler customAuthenticationHandler;

	@Autowired
	private ChexUserDetailService chexUserDetialService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(chexUserDetialService);
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(authenticationProvider())
			.authenticationProvider(apiAuthenticationProvider());	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authenticationProvider(authenticationProvider())
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").hasRole("USER")
			.antMatchers("/login","/registration").permitAll()
			.and()
			.httpBasic()
		.and()
			.formLogin()
			.successHandler(customAuthenticationHandler);
		
		http.csrf().disable()
			.authenticationProvider(apiAuthenticationProvider())
			.authorizeRequests()
				.antMatchers("/api/**").hasAnyRole("ADMIN","USER")
			.and()
				.httpBasic();
				
	}

	//.antMatchers("/**").permitAll() // TEST
	@Bean
	public DaoAuthenticationProvider apiAuthenticationProvider() {
		DaoAuthenticationProvider apiAuthenticationProvider = new DaoAuthenticationProvider();
		apiAuthenticationProvider.setUserDetailsService(chexUserDetialService);
		apiAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return apiAuthenticationProvider;
	}


	
}
