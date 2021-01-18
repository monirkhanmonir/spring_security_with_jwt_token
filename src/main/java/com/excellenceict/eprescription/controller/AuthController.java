package com.excellenceict.eprescription.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellenceict.eprescription.anotation.ApiController;
import com.excellenceict.eprescription.dto.LoginDto;
import com.excellenceict.eprescription.dto.Response;
import com.excellenceict.eprescription.service.interF.AuthService;
import com.excellenceict.eprescription.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.AuthManager.ROOT)
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@GetMapping(UrlConstraint.AuthManager.LOGIN)
	public Response getLogin(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Request accept");
		return authService.login(loginDto);
	}
}
