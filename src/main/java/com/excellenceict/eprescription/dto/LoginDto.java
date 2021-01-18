package com.excellenceict.eprescription.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginDto {

	@NotBlank(message = "Username mandatory")
	private String username;
	@NotBlank(message = "Password mandatory")
	private String password;
	
}
