package io.wizard.evotingapp.party.repository;

import io.wizard.evotingapp.party.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    boolean existsByCandidateId(String generatedId);
}
