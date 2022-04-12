package com.r2system.app.SpringSecurityTest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home")
public class HomeController {


    @GetMapping("index")
    public String index(){
        return "Index page";
    }
}
