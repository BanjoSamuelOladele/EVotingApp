package io.wizard.evotingapp.voter.repository;

import io.wizard.evotingapp.voter.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoterRepository extends MongoRepository<Voter, String> {
    boolean existsVoterByVoterId(String generatedId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Voter> findByEmail(String email);
    Optional<Voter> findByVoterId(String voterId);
}
