package io.wizard.evotingapp.party.repository;

import io.wizard.evotingapp.party.Party;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PartyRepository extends MongoRepository<Party, String> {
    boolean existsByPartyId(String generatedId);
    @Query(value = "{'partyName': {$regex :  ?0, $options: 'i'}}")
    Optional<Party> findPartyByPartyNameIgnoreCase(String partyName);
    boolean existsByPartyNameIgnoreCase(String partyName);

    boolean existsByPartyAcronymIgnoreCase(String partyAcronym);

    Optional<Party> findByPartyAcronymIgnoreCase(String partyAcronym);
}