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

    public Pizza createPizza(Pizza formPizza){
        Pizza newPizza = new Pizza(formPizza);
        return pizzaRepository.save(newPizza);
    }

    public Pizza updatePizza(Pizza formPizza, Integer id) throws  PizzaNotFoundException {
        Pizza pizzaToUpdate = pizzaRepository.findById(id).orElseThrow(PizzaNotFoundException::new);
        pizzaToUpdate.copyFrom(formPizza);
        return pizzaRepository.save(pizzaToUpdate);
    }

    public boolean isValidName(Pizza formPizza){
        if (formPizza.getId() == null) {
            return !pizzaRepository.existsByName(formPizza.getName());
        }
        return !pizzaRepository.existsByNameAndIdNot(formPizza.getName(), formPizza.getId());
    }

    public boolean deleteById(Integer id) throws  PizzaNotFoundException {
        pizzaRepository.findById(id).orElseThrow(PizzaNotFoundException::new);
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
