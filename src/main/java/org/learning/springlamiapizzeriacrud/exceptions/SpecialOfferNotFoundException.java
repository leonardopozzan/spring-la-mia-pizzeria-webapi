package org.learning.springlamiapizzeriacrud.exceptions;

public class SpecialOfferNotFoundException extends RuntimeException{
    public SpecialOfferNotFoundException(){
        super();
    }
    public SpecialOfferNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
