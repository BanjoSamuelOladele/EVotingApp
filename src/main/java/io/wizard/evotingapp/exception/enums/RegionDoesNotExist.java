package io.wizard.evotingapp.exception.enums;

public class RegionDoesNotExist extends RuntimeException{
    public RegionDoesNotExist(String message){
        super(message);
    }
}
