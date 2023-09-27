package io.wizard.evotingapp.exception.enums;

public class InvalidStateException extends RuntimeException{
    public InvalidStateException(String message){
        super(message);
    }
}
