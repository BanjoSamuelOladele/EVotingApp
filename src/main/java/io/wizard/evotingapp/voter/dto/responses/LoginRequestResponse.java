package io.wizard.evotingapp.voter.dto.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder@Setter@Getter
public class LoginRequestResponse {
    private boolean isLoggedIn;
    private String message;
}
