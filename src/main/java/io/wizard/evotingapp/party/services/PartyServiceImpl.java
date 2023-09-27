package io.wizard.evotingapp.party.services;
import io.wizard.evotingapp.exception.party.DuplicatePartyAcronymException;
import io.wizard.evotingapp.exception.party.DuplicatePartyNameException;
import io.wizard.evotingapp.party.Party;
import io.wizard.evotingapp.party.dto.request.RegisterPartyRequest;
import io.wizard.evotingapp.party.dto.response.RegisterPartyResponse;
import io.wizard.evotingapp.party.repository.PartyRepository;
import io.wizard.evotingapp.utils.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService{
    private final PartyRepository partyRepository;
    @Override
    public RegisterPartyResponse registerParty(RegisterPartyRequest request) {
        checkForDuplicate(request);
        request.setPartyId(generateId());
        ModelMapper mapper = new ModelMapper();
        Party party = mapper.map(request, Party.class);
        Party savedParty = partyRepository.save(party);
        return RegisterPartyResponse
                .builder().partyAcronym(savedParty.getPartyAcronym())
                .partyId(savedParty.getPartyId())
                .message("Successfully registered...")
                .build();
    }
    @Override
    public Party findPartyByPartyAcronym(String partyAcronym) {
        Optional<Party> foundParty =
                partyRepository.
                        findByPartyAcronymIgnoreCase(partyAcronym);
        return foundParty.get();
    }
    private void checkForDuplicate(RegisterPartyRequest request) {
        boolean check =
                partyRepository.existsByPartyNameIgnoreCase(request.getPartyName());
        if (check) {
            throw new DuplicatePartyNameException("Party with " + request.getPartyName() + " already exist...");
        }
        boolean checkIt = partyRepository.existsByPartyAcronymIgnoreCase(request.getPartyAcronym());
        if (checkIt) {
            throw new DuplicatePartyAcronymException("Party with acronym " +
                            request.getPartyAcronym() + " already exists...");
        }
    }
    private String generateId(){
        String generatedId = IdGenerator.generatePartyId();
        boolean check = partyRepository.existsByPartyId(generatedId);
        if (check){
            generateId();
        }
        return generatedId;
    }
}
