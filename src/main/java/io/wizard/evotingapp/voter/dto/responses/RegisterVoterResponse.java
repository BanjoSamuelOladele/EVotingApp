package io.wizard.evotingapp.voter.dto.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder@Getter@Setter
public class RegisterVoterResponse {
    private int statusCOde;
    private String voterId;
    private String message;
}
