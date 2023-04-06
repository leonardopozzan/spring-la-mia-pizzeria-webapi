package org.learning.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.exceptions.SpecialOfferNotFoundException;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.learning.springlamiapizzeriacrud.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {
    @Autowired
    SpecialOfferService specialOfferService;

    @Autowired
    PizzaService pizzaService;

    @GetMapping("/create")
    public String create(Model model, @RequestParam(name = "pizzaId")Optional<Integer> id){
        if (id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "è necessario passare un Id di una pizza per creare un offerta");
        }
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setStartingDate(LocalDate.now());
        try{
            Pizza pizza = pizzaService.getPizzaById(id.get());
            specialOffer.setPizza(pizza);
        }catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id" + id.get() + " non trovata");

        }
        model.addAttribute("specialOffer", specialOffer);
        return "offers/editCreate";
    }

    @PostMapping("/create")
    public String doCreate(Model model,@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "offers/editCreate";
        }
        SpecialOffer specialOffer = specialOfferService.createSpecialOffer(formSpecialOffer);
        return "redirect:/menu/" + Integer.toString(specialOffer.getPizza().getId());
    }

    @GetMapping("/edit/{id}")
    public String edit (Model model, @PathVariable Integer id){
        try{
            SpecialOffer specialOffer = specialOfferService.getSpecialOfferById(id);
            model.addAttribute("specialOffer", specialOffer);
            return "offers/editCreate";
        } catch (SpecialOfferNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta con id" + id + " non trovata");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(Model model,@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, @PathVariable Integer id){
        if(bindingResult.hasErrors()){
            return "offers/editCreate";
        }

        try{
            SpecialOffer updatedSpecialOffer = specialOfferService.updateSpecialOffer(formSpecialOffer , id);
            return "redirect:/menu/" + Integer.toString(updatedSpecialOffer.getPizza().getId());
        } catch (SpecialOfferNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta con id" + formSpecialOffer.getPizza().getId() + " non trovata");
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        try {
            Integer pizzaId = specialOfferService.delete(id);
            return "redirect:/menu/" + Integer.toString(pizzaId);
        } catch (SpecialOfferNotFoundException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta con id" + id + " non trovata");
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossibile eliminare l'offerta con id" + id);
        }
    }
}
