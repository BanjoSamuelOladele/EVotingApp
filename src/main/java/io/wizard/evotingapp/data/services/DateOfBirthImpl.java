package io.wizard.evotingapp.data.services;

import io.wizard.evotingapp.data.DateOfBirth;
import io.wizard.evotingapp.data.repository.DateOfBirthRepository;
import io.wizard.evotingapp.utils.Mapper;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DateOfBirthImpl implements DateOfBirthService{
    private final DateOfBirthRepository repository;
    @Override
    public DateOfBirth saveDateOfBirth(RegisterVoterRequest request) {
        DateOfBirth createdDate = Mapper.checkDateOfBirth(request);
        return repository.save(createdDate);
    }
}
