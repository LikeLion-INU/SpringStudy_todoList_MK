package com.example.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/loginHome")
    public String LoginHome(){
        return "login";
    }

    @GetMapping("/joinHome")
    public String join(){
        return "join";
    }
}
