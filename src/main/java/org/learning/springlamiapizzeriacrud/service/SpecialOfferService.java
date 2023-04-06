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
//        SpecialOffer newSpecialOffer = new SpecialOffer(formSpecialOffer);
        return specialOfferRepository.save(formSpecialOffer);
    }

    public SpecialOffer getSpecialOfferById(Integer id) throws SpecialOfferNotFoundException{
        return specialOfferRepository.findById(id).orElseThrow(SpecialOfferNotFoundException::new);
    }

    public SpecialOffer updateSpecialOffer(SpecialOffer formSpecialOffer, Integer id) throws SpecialOfferNotFoundException{
        SpecialOffer specialOfferToUpdate = specialOfferRepository.findById(id).orElseThrow(SpecialOfferNotFoundException::new);
        specialOfferToUpdate.copyFrom(formSpecialOffer);
        return specialOfferRepository.save(specialOfferToUpdate);
    }

    public Integer delete(Integer specialOfferId) throws SpecialOfferNotFoundException{
        SpecialOffer specialOfferToDelete = specialOfferRepository.findById(specialOfferId).orElseThrow(SpecialOfferNotFoundException::new);
        specialOfferRepository.delete(specialOfferToDelete);
        return specialOfferToDelete.getPizza().getId();
    }
}
