package io.wizard.evotingapp.data.services;

import io.wizard.evotingapp.data.Address;
import io.wizard.evotingapp.data.repository.AddressRepository;
import io.wizard.evotingapp.utils.Mapper;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;
    @Override
    public Address saveAddress(RegisterVoterRequest request) {
        Address address =  Mapper.createAddress(request);
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }
}
