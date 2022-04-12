package com.r2system.app.SpringSecurityTest.service;


import com.r2system.app.SpringSecurityTest.config.auth.UserPrincipal;
import com.r2system.app.SpringSecurityTest.model.Roles;
import com.r2system.app.SpringSecurityTest.model.Users;
import com.r2system.app.SpringSecurityTest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {



    @Autowired
    private UserRepository _repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Users user =  _repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));

        Set<GrantedAuthority> authorities =  user.getRoles().stream().map(x-> new SimpleGrantedAuthority(x.getName().toString()))
                                   .collect(Collectors.toSet());

        log.debug(authorities.toString());
       if(authorities.isEmpty()) {
            log.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        return UserPrincipal.builder()
                .user(user)
                .authorities(authorities)
                .build();

    }
}
