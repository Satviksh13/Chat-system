package com.Login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/login")
    public String login(){
        return "loginpage";
    }

    @GetMapping("/home")
    public String home(){
        return "homepage";
    }
}
