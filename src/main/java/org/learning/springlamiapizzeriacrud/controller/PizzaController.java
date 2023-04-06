package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.model.AlertMessage;
import org.learning.springlamiapizzeriacrud.model.Ingredient;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.service.IngredientService;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.learning.springlamiapizzeriacrud.model.AlertMessage.AlertMessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/menu")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;
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
    public String show(Model model, @PathVariable Integer id){
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("pizza", pizza);
            return "pizzas/show";
        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id" + id + " non trovata");
        }
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "pizzas/editCreate";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
        if(!pizzaService.isValidName(formPizza)){
            bindingResult.addError(new FieldError("pizza", "name", formPizza.getName(), false, null, null, "Il nome deve essere unico"));
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientService.getAllIngredients());
            return "pizzas/editCreate";
        }
        Pizza createdPizza = pizzaService.createPizza(formPizza);
        return "redirect:/menu/"+ Integer.toString(createdPizza.getId());
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            model.addAttribute("pizza" , pizza);
            model.addAttribute("ingredients", ingredientService.getAllIngredients());
            return "pizzas/editCreate";
        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id" + id + " non trovata");
        }
    }

    @PostMapping("edit/{id}")
    public String doEdit(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,Model model, @PathVariable Integer id){
        if(!pizzaService.isValidName(formPizza)){
            bindingResult.addError(new FieldError("pizza", "name", formPizza.getName(), false, null, null, "Il nome deve essere unico"));
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientService.getAllIngredients());
            System.out.println(bindingResult);
            return "pizzas/editCreate";
        }

        try{
            Pizza updatedPizza = pizzaService.updatePizza(formPizza, id);
            return "redirect:/menu/" + Integer.toString(updatedPizza.getId());

        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id" + id + " non trovata");
        }
    }

    @GetMapping("delete/{id}")
    public String doDelete (@PathVariable Integer id,  RedirectAttributes redirectAttributes ){
        try {
            boolean success = pizzaService.deleteById(id);
            if (success) {
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.SUCCESS, "Pizza con " + id + " eliminata con succeso");
                redirectAttributes.addFlashAttribute("message", alertMessage);

            } else {
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.ERROR, "Impossibile eliminare la pizza con id " + id);
                redirectAttributes.addFlashAttribute("message", alertMessage);
            }
        } catch (PizzaNotFoundException e){
            AlertMessage alertMessage = new AlertMessage(AlertMessageType.ERROR, "Pizza con id" + id + " non trovata");
            redirectAttributes.addFlashAttribute("message", alertMessage);
        }
        return "redirect:/menu";
    }

}
