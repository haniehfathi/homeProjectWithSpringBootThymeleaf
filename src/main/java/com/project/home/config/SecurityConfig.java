package com.project.home.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login","/","/logout","/register","/css/**", "/js/**", "/img/**","/home/**","/getCities/**")
		.permitAll()
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/login").failureUrl("/login-error").successForwardUrl("/")
		.usernameParameter("email").permitAll()
		.and().rememberMe()
		.and().exceptionHandling().accessDeniedPage("/errorPage")
		.and().logout().deleteCookies("remember-me")
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	auth.jdbcAuthentication().
	dataSource(dataSource)
    .passwordEncoder(new BCryptPasswordEncoder())
	.usersByUsernameQuery("select email,password,enabled from user where email=?")
	.authoritiesByUsernameQuery("select user_email,role from authorities where user_email=?");
	}
	
	
	

	
}
