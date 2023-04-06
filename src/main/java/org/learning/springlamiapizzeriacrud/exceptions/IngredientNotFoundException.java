package org.learning.springlamiapizzeriacrud.exceptions;

public class IngredientNotFoundException extends RuntimeException{
    public IngredientNotFoundException(){
        super();
    }
    public IngredientNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
