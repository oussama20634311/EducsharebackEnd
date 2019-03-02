package com.educshare.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
   
	// methode pour signature le JWT d'apres le droit d'accès et connecte entre deux partie back et frond
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// pr accepter tous les domains de la requette
		response.addHeader("Access-Control-Allow-Origin", "*");
		// pr autoriser les entetes
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,"
				+ "Acess-Control-Request-Method," + "Acces-Control-Request-Headers," + "Authorization");

		// pr exposer les entetes
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin," + "Acess-Control-Allow-Credentials,Authorization");
        
		//le methode de autorisation pour afficher le Token
		response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");
		
		// si la request est envoier avec OPTIONS on retourne ok executer tout response.addHeader
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else if (request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		} 
		else {
			// 2 eme filre pour autorization
			String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);

			System.out.println("Token jwt >>" + jwtToken);
                //JWT equal null ou jwt ne commance pas PREFIX alros n'accepte pas return filterChain
			if (jwtToken == null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
			
			String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
			DecodedJWT decodedJWT = verifier.verify(jwt);
			System.out.println("JWT = HEADER_PREFIX " + decodedJWT);
			String username = decodedJWT.getSubject();
			
			List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

			System.out.println("username=" + username);
			System.out.println("roles=" + roles);
			System.out.println("JWT = HEADER_PREFIX " + decodedJWT);
			// authorities de type role JWT.IO
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(rn -> {
				authorities.add(new SimpleGrantedAuthority(rn));
			});
			
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
			filterChain.doFilter(request, response);
		}
	}
}
