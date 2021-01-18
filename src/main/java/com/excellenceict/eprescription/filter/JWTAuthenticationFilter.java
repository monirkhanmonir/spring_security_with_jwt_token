 package com.excellenceict.eprescription.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.excellenceict.eprescription.dao.UserInfoRepository;
import com.excellenceict.eprescription.model.UserInfo;
import com.excellenceict.eprescription.service.CustomUserDetailsService;

@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = getJwtFromrequest(request);
			if(StringUtils.hasText(jwt) && jwtTokenProvider.isValidToken(jwt)) {
				
				Long userid = jwtTokenProvider.getUserIdFromToken(jwt);
				System.out.println("User Id: "+userid);
				UserInfo user = userInfoRepository.findById(userid).get();
				UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
				UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}		
		} catch (Exception e) {
			// TODO: handle exception
		} 
		filterChain.doFilter(request, response);
		
	}
	
	private String getJwtFromrequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		System.out.println("Request token: "+bearerToken.substring(7));
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			System.out.println("Request token: "+bearerToken.substring(7));
			return bearerToken.substring(7);
		}
		return null;
	}

}
