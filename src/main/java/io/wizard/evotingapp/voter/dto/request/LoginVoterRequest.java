package io.wizard.evotingapp.voter.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder@Setter@Getter
public class LoginVoterRequest {
    private String email;
    private String password;
}
