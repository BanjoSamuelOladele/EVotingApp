package io.wizard.evotingapp.exception.voter;

public class UnderEighteenException extends RuntimeException{
    public UnderEighteenException(String message){
        super(message);
    }
}
