package org.learning.springlamiapizzeriacrud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String welcome(){

        return "welcome";
    }
}
