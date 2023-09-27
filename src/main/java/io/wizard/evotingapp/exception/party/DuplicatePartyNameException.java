package io.wizard.evotingapp.exception.party;

public class DuplicatePartyNameException extends RuntimeException{
    public DuplicatePartyNameException(String message){
        super(message);
    }
}
