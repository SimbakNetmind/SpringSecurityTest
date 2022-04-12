package com.r2system.app.SpringSecurityTest.service;

import com.r2system.app.SpringSecurityTest.config.auth.UserPrincipal;
import com.r2system.app.SpringSecurityTest.config.jwt.IJwtProvider;
import com.r2system.app.SpringSecurityTest.dto.request.LoginRequest;
import com.r2system.app.SpringSecurityTest.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService  implements  IAuthenticationService{


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;


    @Override
    public Optional<Users> singInReturnJwt(LoginRequest requests) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requests.getUsername(), requests.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

       Optional<Users>  signInUser = Optional.ofNullable(userPrincipal.getUser());

        signInUser.orElseThrow(()-> new UsernameNotFoundException(signInUser.get().getUsername()) )
                .setToken(jwt);

        return signInUser;
    }
}
