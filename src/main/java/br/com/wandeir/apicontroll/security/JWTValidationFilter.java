package br.com.wandeir.apicontroll.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidationFilter extends BasicAuthenticationFilter {

	public JWTValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
										HttpServletResponse response, 
										FilterChain chain) throws IOException, ServletException {
		String atribute = request.getHeader(JWTUtils.HEAD_ATRIBUTE);
		if(atribute == null) {
			chain.doFilter(request, response);
			return;
		}
		
		if(!atribute.startsWith(JWTUtils.ATRIBUTE_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = atribute.replace(JWTUtils.ATRIBUTE_PREFIX,"");
		
		UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
		String user = JWT.require(Algorithm.HMAC512(JWTUtils.TOKEN_PASSWORD))
				.build().verify(token).getSubject();
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	}

}
