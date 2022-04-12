package com.r2system.app.SpringSecurityTest.controller;

import com.r2system.app.SpringSecurityTest.dto.request.LoginRequest;
import com.r2system.app.SpringSecurityTest.model.Users;
import com.r2system.app.SpringSecurityTest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request)
    {

        Optional<Users> user = authenticationService.singInReturnJwt(request);

        if(user == null){
            return new ResponseEntity<>("message:not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
