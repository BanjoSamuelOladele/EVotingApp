package io.wizard.evotingapp.exception.voter;

public class ExistingVoterEmailException extends RuntimeException{
    public ExistingVoterEmailException(String message){
        super(message);
    }
}
