package br.com.wandeir.apicontroll.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wandeir.apicontroll.data.UserDetailsData;
import br.com.wandeir.apicontroll.model.UserModel;

public class JWTAutenticateFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authManager;

	public JWTAutenticateFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserModel user = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
			return authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(),  user.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Falha na autenticação do usuário", e);
		}		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetailsData userData = (UserDetailsData) authResult.getPrincipal();
		String token = JWT.create().withSubject(userData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JWTUtils.TOKEN_EXPIRATION))
				.withClaim("Role", "ADMIN")
				.sign(Algorithm.HMAC512(JWTUtils.TOKEN_PASSWORD));		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
	
	
}
