package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/menu")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping()
    public String index(Model model, @RequestParam( name = "name") Optional<String> name){
        List<Pizza> pizzas = new ArrayList<>();
        if(name.isEmpty()){
            pizzas = pizzaService.getAllPizzas();
        }else {
            pizzas = pizzaService.getFilteredPizzas(name.get());
            model.addAttribute("keyword" , name.get());
        }
        model.addAttribute("list" , pizzas);
        return "pizzas/index";
    }
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id){
        Pizza pizza;
        try {
            pizza = pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id" + id + " non trovata");
        }
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "pizzas/create";
        }
        pizzaService.createPizza(formPizza);
        model.addAttribute("success", true);
        return "redirect:/menu";
    }
}
