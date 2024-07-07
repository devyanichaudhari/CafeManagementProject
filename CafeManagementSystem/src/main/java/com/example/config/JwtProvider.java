package com.example.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider 
{
	private SecretKey key=Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
	@SuppressWarnings("deprecation")
	public String generateToken(Authentication Auth)
	{
		String jwt=Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email", Auth.getName())
				.signWith(key).compact();
		return jwt;		
	}
	@SuppressWarnings("deprecation")
	public String getEmailFromJwtToken(String jwt)
	{
		jwt=jwt.substring(7);
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email=String.valueOf(claims.get("email"));
		return email;
	}
}
