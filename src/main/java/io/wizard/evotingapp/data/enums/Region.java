package io.wizard.evotingapp.data.enums;

import io.wizard.evotingapp.exception.enums.InvalidStateException;
import io.wizard.evotingapp.exception.enums.RegionDoesNotExist;

public enum Region {
    NORTH_WEST("Jigawa", "Kaduna", "Kano", "Kastina", "Kebbi", "Sokoto", "Zamfara"),
    NORTH_EAST("Adamawa", "Bauchi", "Borno", "Gombe", "Taraba", "Yobe"),
    NORTH_CENTRAL("Benue", "FCT", "Kogi", "Kwara", "Nasarawa", "Niger", "Plataeu"),
    SOUTH_SOUTH("Akwa_Ibom", "Bayelsa", "Cross_River", "Delta", "Edo", "Rivers"),
    SOUTH_EAST("Abia", "Anambra", "Ebonyi", "Enugu", "Imo"),
    SOUTH_WEST("Lagos", "Ekiti", "Ondo", "Osun", "Oyo", "Ogun");

    private final String[] states;
    Region(String... states){
        this.states = states;
    }
    public static String getState(String state){
        for (Region region : Region.values()){
            for (String baseState : region.states){
                if (baseState.equalsIgnoreCase(state)) return baseState;
            }
        }
        throw new InvalidStateException("State entered not valid...");
    }
    public static Region getRegion(String state){
        for (Region region : Region.values()){
            for (String baseState : region.states){
                if (baseState.equalsIgnoreCase(state)) return region;
            }
        }
        throw new RegionDoesNotExist("Region does not exist...");
    }
}
