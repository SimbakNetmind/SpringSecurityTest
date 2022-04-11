package com.r2system.app.SpringSecurityTest.config;

import com.r2system.app.SpringSecurityTest.service.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JpaUserDetailService userDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.userDetailsService(userDetailService)
               .passwordEncoder(passwordEncoder());


    }
}
