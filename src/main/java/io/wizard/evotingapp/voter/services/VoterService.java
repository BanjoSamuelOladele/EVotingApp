package io.wizard.evotingapp.voter.services;

import io.wizard.evotingapp.voter.Voter;
import io.wizard.evotingapp.voter.dto.request.LoginVoterRequest;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import io.wizard.evotingapp.voter.dto.responses.LoginRequestResponse;
import io.wizard.evotingapp.voter.dto.responses.RegisterVoterResponse;

public interface VoterService {
    RegisterVoterResponse register(RegisterVoterRequest request);
    LoginRequestResponse login(LoginVoterRequest request);

    Voter findVoterByVoterId(String voterId);
}
