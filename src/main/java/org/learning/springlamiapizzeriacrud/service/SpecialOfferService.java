package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.exceptions.SpecialOfferNotFoundException;
import org.learning.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
    @Autowired
    SpecialOfferRepository specialOfferRepository;

    public SpecialOffer createSpecialOffer(SpecialOffer formSpecialOffer){
        SpecialOffer newSpecialOffer = new SpecialOffer(formSpecialOffer);
        return specialOfferRepository.save(newSpecialOffer);
    }

    public SpecialOffer getSpecialOfferById(Integer id) throws SpecialOfferNotFoundException{
        return specialOfferRepository.findById(id).orElseThrow(SpecialOfferNotFoundException::new);
    }

    public SpecialOffer updateSpecialOffer(SpecialOffer formSpecialOffer) throws SpecialOfferNotFoundException{
        SpecialOffer specialOfferToUpdate = specialOfferRepository.findById(formSpecialOffer.getPizza().getId()).orElseThrow(SpecialOfferNotFoundException::new);
        specialOfferToUpdate.copyFrom(formSpecialOffer);
        return specialOfferRepository.save(specialOfferToUpdate);
    }
}
