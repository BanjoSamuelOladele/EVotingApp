package io.wizard.evotingapp.party.services;

import io.wizard.evotingapp.party.Party;
import io.wizard.evotingapp.party.dto.request.RegisterPartyRequest;
import io.wizard.evotingapp.party.dto.response.RegisterPartyResponse;

public interface PartyService {
    RegisterPartyResponse registerParty(RegisterPartyRequest request);
    Party findPartyByPartyAcronym(String partAcronym);
}
