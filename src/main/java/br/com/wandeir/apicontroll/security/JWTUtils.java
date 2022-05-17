package br.com.wandeir.apicontroll.security;

public final class JWTUtils {
	
	public static final int TOKEN_EXPIRATION = 600_000;
	
	public static final  String TOKEN_PASSWORD = "be52ff56-71c4-4b04-b5e0-1613809e9e42";
	
	public static final String HEAD_ATRIBUTE = "Authorization";
	
	public static final String ATRIBUTE_PREFIX = "Bearer ";

	private JWTUtils() {		
	}
	
}
