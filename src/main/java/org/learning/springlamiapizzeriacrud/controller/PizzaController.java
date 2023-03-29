package org.learning.springlamiapizzeriacrud.controller;

import jakarta.websocket.server.PathParam;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String welcome(){
        return "welcome";
    }
    @GetMapping("/menu")
    public String index(Model model){
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("list" , pizzas);
        return "pizzas/index";
    }

    @GetMapping("/menu/search")
    public String search(Model model, @RequestParam( name = "name") String name){
        List<Pizza> pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("list" , pizzas);
        return "pizzas/index";
    }


}
