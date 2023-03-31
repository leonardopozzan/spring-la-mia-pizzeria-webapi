package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    public  List<Pizza> getFilteredPizzas(String keyword){
        return pizzaRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Pizza getPizzaById(int id) throws  PizzaNotFoundException{
        return pizzaRepository.findById(id).orElseThrow(PizzaNotFoundException::new);
    }

    public void createPizza(Pizza formPizza){
        Pizza newPizza = new Pizza(formPizza);
        pizzaRepository.save(newPizza);
    }
}