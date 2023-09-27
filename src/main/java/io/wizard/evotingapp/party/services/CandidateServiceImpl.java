package io.wizard.evotingapp.party.services;

import io.wizard.evotingapp.party.Candidate;
import io.wizard.evotingapp.party.Party;
import io.wizard.evotingapp.party.dto.response.RegisterCandidateResponse;
import io.wizard.evotingapp.party.repository.CandidateRepository;
import io.wizard.evotingapp.utils.IdGenerator;
import io.wizard.evotingapp.utils.Mapper;
import io.wizard.evotingapp.voter.Voter;
import io.wizard.evotingapp.voter.services.VoterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static io.wizard.evotingapp.utils.Mapper.map;
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService{
    private final CandidateRepository candidateRepository;
    private final PartyServiceImpl partyService;
    private final VoterServiceImpl voterService;
    @Override
    public RegisterCandidateResponse registerCandidate(String voterId, String partAcronym) {
       Voter voter = voterService.findVoterByVoterId(voterId);
       Party party = partyService.findPartyByPartyAcronym(partAcronym);
       Candidate candidate = map(voter, party);
       candidate.setCandidateId(generateId(partAcronym));
       Candidate savedCandidate = candidateRepository.save(candidate);
       return map(savedCandidate, party);
    }
    private String generateId(String partyAcronym){
        String generatedId = IdGenerator.generateCandidateId(partyAcronym);
        boolean check = candidateRepository.existsByCandidateId(generatedId);
        if (check){
            generateId(partyAcronym);
        }
        return generatedId;
    }
}
