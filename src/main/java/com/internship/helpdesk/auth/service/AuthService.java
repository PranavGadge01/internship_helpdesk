package com.internship.helpdesk.auth.service;

import com.internship.helpdesk.auth.dto.LoginRequest;
import com.internship.helpdesk.auth.dto.LoginResponse;

public interface AuthService {

    public LoginResponse login(LoginRequest request);

}
