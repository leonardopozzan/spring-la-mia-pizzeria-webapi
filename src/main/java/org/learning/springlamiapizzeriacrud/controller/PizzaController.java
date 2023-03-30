package org.learning.springlamiapizzeriacrud.controller;

import jakarta.websocket.server.PathParam;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public String index(Model model, @RequestParam( name = "name") Optional<String> name){
        List<Pizza> pizzas = new ArrayList<>();
        if(name.isEmpty()){
            pizzas = pizzaRepository.findAll();
        }else {
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(name.get());
            model.addAttribute("keyword" , name.get());
        }
        model.addAttribute("list" , pizzas);
        return "pizzas/index";
    }
    @GetMapping("/menu/{id}")
    public String show(Model model, @PathVariable int id){
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("nessuna pizza trovata"));
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }
}
