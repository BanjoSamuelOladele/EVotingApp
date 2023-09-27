package io.wizard.evotingapp.data.repository;

import io.wizard.evotingapp.data.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
