package org.learning.springlamiapizzeriacrud.exceptions;

public class PizzaNotFoundException extends RuntimeException{
    public PizzaNotFoundException(String errorMessage){
        super(errorMessage);
    }
    public PizzaNotFoundException(){
        super();
    }
}
