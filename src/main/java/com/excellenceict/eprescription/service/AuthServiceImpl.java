package com.excellenceict.eprescription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.excellenceict.eprescription.dao.UserInfoRepository;
import com.excellenceict.eprescription.dto.LoginDto;
import com.excellenceict.eprescription.dto.LoginResponseDto;
import com.excellenceict.eprescription.dto.Response;
import com.excellenceict.eprescription.filter.JwtTokenProvider;
import com.excellenceict.eprescription.model.UserInfo;
import com.excellenceict.eprescription.service.interF.AuthService;
import com.excellenceict.eprescription.util.ResponseBuilder;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	UserInfoRepository userRepo;
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtTokenProvider tokenProvider;
	
	
	@Override
	public Response login(LoginDto logindto) {
		
		UserInfo user = userRepo.findByUsername(logindto.getUsername());
		if(user == null) {
			return ResponseBuilder.getFailorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
		}
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		if(authentication.isAuthenticated()) {
			LoginResponseDto loginResponseDto =  new LoginResponseDto();
			loginResponseDto.setToken(tokenProvider.generateToken(authentication));
			loginResponseDto.setUsername(user.getUsername());
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Login success", loginResponseDto);
		}
		return ResponseBuilder.getFailorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
	}

}
