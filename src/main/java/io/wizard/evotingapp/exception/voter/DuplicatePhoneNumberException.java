package io.wizard.evotingapp.exception.voter;

public class DuplicatePhoneNumberException extends RuntimeException{
    public DuplicatePhoneNumberException(String message){
        super(message);
    }
}
