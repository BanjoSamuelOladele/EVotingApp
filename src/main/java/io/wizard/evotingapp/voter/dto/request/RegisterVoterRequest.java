package io.wizard.evotingapp.voter.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Builder
public class RegisterVoterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String state;
    private String lga;
    private String yearOfBirth;
    private String monthOfBirth;
    private String dayOfBirth;
}
