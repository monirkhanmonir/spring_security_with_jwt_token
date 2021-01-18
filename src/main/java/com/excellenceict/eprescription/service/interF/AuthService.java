package com.excellenceict.eprescription.service.interF;

import com.excellenceict.eprescription.dto.LoginDto;
import com.excellenceict.eprescription.dto.Response;

public interface AuthService {
	Response login(LoginDto logindto);
}
