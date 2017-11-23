package com.bridgelabz.token;

import java.util.Date;

import com.bridgelabz.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {
	private static String secretekey = "deepak";

	public static String generateToken(int userId, User user) {
		long nowMillis = System.currentTimeMillis();
		Date dateAndTime = new Date(nowMillis);
		String issuer = dateAndTime.toString();
		JwtBuilder token = Jwts.builder().setId(Integer.toString(userId)).setIssuedAt(dateAndTime).setIssuer(issuer)
				.signWith(SignatureAlgorithm.HS256, secretekey);
		return token.toString();
	}
	
	public int verifyToken(String token)
	{
		Claims claims = Jwts.parser().setSigningKey(secretekey).parseClaimsJws(token).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		return Integer.parseInt(claims.getId());
	}
}
