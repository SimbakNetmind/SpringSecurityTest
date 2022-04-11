package com.r2system.app.SpringSecurityTest.service;

import com.r2system.app.SpringSecurityTest.model.Roles;
import com.r2system.app.SpringSecurityTest.model.Users;
import com.r2system.app.SpringSecurityTest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {



    @Autowired
    private UserRepository _repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Users> user =  _repository.findByUsername(username);

        if(user == null){
          log.error("Error en el Login: no existe el usuario '\" + username + \"' en el sistema!");
          throw new UsernameNotFoundException("Username: \" + username + \" no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Roles role : user.get().getRoles()){

            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }

        if(authorities.isEmpty()) {
            log.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }

        return new User(user.get().getUsername(),user.get().getPassword(), user.get().isEnabled(), true, true, true, authorities);
    }
}
