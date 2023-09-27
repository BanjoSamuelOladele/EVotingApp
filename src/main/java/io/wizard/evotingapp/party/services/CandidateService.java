package io.wizard.evotingapp.party.services;

import io.wizard.evotingapp.party.dto.response.RegisterCandidateResponse;

public interface CandidateService {
    RegisterCandidateResponse registerCandidate(String voterId, String partAcronym);
}