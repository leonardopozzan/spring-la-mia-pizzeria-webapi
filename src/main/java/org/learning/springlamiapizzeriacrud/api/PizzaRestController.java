package org.learning.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.exceptions.IngredientNotFoundException;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/menu")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(@RequestParam(name = "name")Optional<String> search){
        if (search.isPresent())
            return pizzaService.getFilteredPizzas(search.get());
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id){
        try {
            return pizzaService.getPizzaById(id);
        } catch(PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public Pizza doCreate(@Valid @RequestBody Pizza newPizza){
        if(!pizzaService.isValidName(newPizza))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"errors\": \"il nome deve essere unico\"}");
        try {
            return pizzaService.createPizza(newPizza);
        } catch (IngredientNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Pizza doEdit(@PathVariable Integer id, @Valid @RequestBody Pizza newPizza){
        if(!pizzaService.isValidName(newPizza))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"errors\": \"il nome deve essere unico\"}");
        try {
            return pizzaService.updatePizza(newPizza, id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable Integer id) {
        try {
            boolean success = pizzaService.deleteById(id);
            if (!success)
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossibile eliminare la pizza per offerte collegate ad essa");
            return Map.of("success", true);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

