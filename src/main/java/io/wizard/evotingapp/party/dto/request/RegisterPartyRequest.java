package io.wizard.evotingapp.party.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder@Getter@Setter
public class RegisterPartyRequest {
    private String partyId;
    private String partyName;
    private String partyAcronym;
}
