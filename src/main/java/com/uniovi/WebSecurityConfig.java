package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/css/**", "/img/**", "/script/**", "/", "/signup", "/login/**").permitAll()
				.antMatchers("/user/offer/**").hasAuthority("ROLE_USER")
				.antMatchers("/user/buys/**").hasAuthority("ROLE_USER")
				.antMatchers("/offer/**").hasAnyAuthority("ROLE_USER")
				.antMatchers("/offer/**").hasAnyAuthority("ROLE_USER")
				.antMatchers("/user/list/**").hasAnyAuthority("ROLE_ADMIN")		
				.antMatchers("/user/details/**").hasAnyAuthority("ROLE_ADMIN")		
				.antMatchers("/user/edit/**").hasAnyAuthority("ROLE_ADMIN")				
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home")
				.and()
				.exceptionHandling().accessDeniedPage("/errors/403.html")
				.and()
				.logout().permitAll();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
