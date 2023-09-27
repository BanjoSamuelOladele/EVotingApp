package io.wizard.evotingapp.election.repository;

import io.wizard.evotingapp.election.Election;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElectionRepository extends MongoRepository<Election, String> {
}
