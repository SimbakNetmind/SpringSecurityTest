package com.r2system.app.SpringSecurityTest.service;

import com.r2system.app.SpringSecurityTest.dto.request.LoginRequest;
import com.r2system.app.SpringSecurityTest.model.Users;

import java.util.Optional;

public interface IAuthenticationService {

    Optional<Users> singInReturnJwt(LoginRequest requests);
}
