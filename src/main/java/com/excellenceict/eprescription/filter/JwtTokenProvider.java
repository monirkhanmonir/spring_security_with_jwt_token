package com.excellenceict.eprescription.filter;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.excellenceict.eprescription.dto.UserPrinciple;
import com.excellenceict.eprescription.util.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtTokenProvider {
	private String secretKey = "SpringTelemedicineApp";
	private Long expireHour = Long.valueOf("3");
	public String generateToken(Authentication  authentication) {
		UserPrinciple  userPrincipal = (UserPrinciple) authentication.getPrincipal();
		Date now = new Date();
		return Jwts.builder().setId(UUID.randomUUID().toString())
				.claim("username", userPrincipal.getUsername())
//				.claim("role", userPrincipal.getAuthorities().stream().map(grantedAuthority ->{}))
				.setSubject(String.valueOf(userPrincipal.getId()))
				.setIssuedAt(now).setExpiration(DateUtils.getExpirationTime(expireHour))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims =  Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token)
					.getBody();
		return Long.valueOf(claims.getSubject());
	}
	
	public Boolean isValidToken(String token) {
			try {
				Jwts.parser().setSigningKey(secretKey);
				return true;
			} catch (Exception e) {
				return false;
			}
	}
}
