package com.educshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // activer la security Web
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService; // c'est un interface pour apple la methode configure
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	    auth.userDetailsService(userDetailsService) // pour implimenter cette interface UserDetailsServiceImpl
	     .passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/login","/signup").permitAll();
		
		http.authorizeRequests().antMatchers("/login/**","/SaveStudentWithAvatar/**",
			       "/SaveStudentWithoutAvatar/**","/SaveProfessorWithAvatar/**",
			       "/SaveProfessorWithoutAvatar/**","/profil/**", "/getUserById/**" ,
			       "/profilWithAvatar/**/edit/**" , "/profilWithoutAvatar/**/edit/**",
			       "/messages/**", "/matters/**").permitAll();
		http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/courses/**","/departments/**" , "/universities/**" , "/users/**" ).hasAuthority("ADMIN");
		// else verrouiller tout le reste
		//http.authorizeRequests().anyRequest().authenticated();
		
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));    
        
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
          
	}
	
	//,"/documents/**", "/favoris/**","/search-documents"
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
