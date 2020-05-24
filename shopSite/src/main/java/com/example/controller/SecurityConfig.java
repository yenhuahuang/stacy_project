package com.example.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
   .authorizeRequests()
    .antMatchers("/css/**", "/index","/").permitAll()
    .antMatchers("/user/**").hasRole("USER")
    .antMatchers("/admin/**").hasRole("ADMIN")
    .and()
   .formLogin()
    .loginPage("/login").failureUrl("/login-error")
   .defaultSuccessUrl("/hello");
 }
  @Autowired
	private DataSource dataSource;

 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 auth
	   .jdbcAuthentication()
	    .dataSource(dataSource);
 }
}