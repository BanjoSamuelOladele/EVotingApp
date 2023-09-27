package io.wizard.evotingapp.data.services;

import io.wizard.evotingapp.data.DateOfBirth;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;

public interface DateOfBirthService {
    DateOfBirth saveDateOfBirth(RegisterVoterRequest request);
}
