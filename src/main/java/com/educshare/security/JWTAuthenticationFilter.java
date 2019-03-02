package com.educshare.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.educshare.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

// ce filtre hérite 
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
 
	//
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
//récupérer le user name et mot de passe et envoyer le format JSON
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// envoyer les donnnes format JSON
			AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);

			System.out.println("username:" + appUser.getEmail());
			System.out.println("Password:" + appUser.getPassword());

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							
							appUser.getEmail(), 
							appUser.getPassword()));
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("PD in request content");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		User user = (User) authResult.getPrincipal(); //retourn automatiquement le user qui est authentifier
		
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities()
		.forEach(a -> {
			roles.add(a.getAuthority());
		});
		//partier Payload + partie signature =>générer le JWT
		String jwt = JWT.create().withIssuer(request.getRequestURI()).withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis() +SecurityParams.EXPIRATION))
				.sign(Algorithm.HMAC256(SecurityParams.SECRET));
		response.addHeader(SecurityParams.JWT_HEADER_NAME,jwt);
	}
}
