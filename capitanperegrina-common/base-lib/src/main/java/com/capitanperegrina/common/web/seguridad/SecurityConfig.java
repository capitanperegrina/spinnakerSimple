package com.capitanperegrina.common.web.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	/**
	 * Because authentication is handled outside the application we don't have to authorize any requests
	 */
	@Override
	@SuppressWarnings("PMD.SignatureDeclareThrowsException")
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.csrf().disable();
	}
}
