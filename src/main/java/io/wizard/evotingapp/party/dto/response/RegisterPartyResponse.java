package io.wizard.evotingapp.party.dto.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder@Setter@Getter
public class RegisterPartyResponse {
    private String partyId;
    private String partyAcronym;
    private String message;
}
