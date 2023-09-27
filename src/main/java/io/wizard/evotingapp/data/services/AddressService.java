package io.wizard.evotingapp.data.services;

import io.wizard.evotingapp.data.Address;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;

public interface AddressService {
    Address saveAddress(RegisterVoterRequest request);
}
