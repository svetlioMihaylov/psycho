package com.question.generator.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import my.pack.account.UserService;

@Configuration
@EnableWebMvcSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key",
				userService());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new StandardPasswordEncoder();
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.eraseCredentials(true).userDetailsService(userService())
		 .passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.antMatchers()
				.antMatchers("/", "/favicon.ico", "/resources/**", "/signup", "/test/*", "/getquestion/*", "/completeTest" , "/validatemail", "/answer" , "/validatemail/resources/dist/js/*", "/validatemail/resources/css/*")
				.permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error=1")
				.loginProcessingUrl("/authenticate").and().logout()
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/login?logout").and().rememberMe()
				.rememberMeServices(rememberMeServices())
				.key("remember-me-key");
		http.csrf().disable();
	}
}