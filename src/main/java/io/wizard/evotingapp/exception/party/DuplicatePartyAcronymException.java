package io.wizard.evotingapp.exception.party;

public class DuplicatePartyAcronymException extends RuntimeException{
    public DuplicatePartyAcronymException(String message){
        super(message);
    }
}
