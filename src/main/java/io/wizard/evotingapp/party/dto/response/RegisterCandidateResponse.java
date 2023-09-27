package io.wizard.evotingapp.party.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder@Setter@Getter
public class RegisterCandidateResponse {
    private String candidateId;
    private String candidateName;
    private String message;
}
