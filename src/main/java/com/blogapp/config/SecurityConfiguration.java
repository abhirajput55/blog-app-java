package com.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogapp.security.CustomUserDetailsService;
import com.blogapp.security.JwtAuthenticationEntry;
import com.blogapp.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration /* extends WebSecurityConfigurerAdapter */ {
	
	/**
	 * The commented line of code is supported till Spring Security 5
	 * After that they deprecated WebSecurityConfigurerAdapter and 
	 * encourage to use component-based security configuration.
	 * For more info visit 
	 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	 */
	
	public static final String[] PUBLIC_URLS = {
		"/api/v1/auth/login",
//		Below url's is related to swagger 
		"/v3/api-docs",	
		"/v2/api-docs",	
		"/swagger-resources/**",	
		"/swagger-ui/**",	
		"/webjars/**",	
	};
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JwtAuthenticationEntry jwtAuthenticationEntry;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf().disable()
		.authorizeHttpRequests()
//		we can use hasAuthority or hasRole to access api by using pattern
//		.antMatchers("api/v1/user/delete/{userId}").hasAuthority("Admin")
		.antMatchers(PUBLIC_URLS).permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntry)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.authenticationProvider(daoAuthenticationProviderBean());
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.build();
		
		return defaultSecurityFilterChain;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http
//		.csrf().disable()
//		.authorizeHttpRequests()
////		we can use hasAuthority or hasRole to access api by using pattern
////		.antMatchers("api/v1/user/delete/{userId}").hasAuthority("Admin")
//		.antMatchers(PUBLIC_URLS).permitAll()
//		.antMatchers(HttpMethod.GET).permitAll()
//		.anyRequest()
//		.authenticated()
//		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntry)
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//	
//		return super.authenticationManagerBean();
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProviderBean() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	

}
