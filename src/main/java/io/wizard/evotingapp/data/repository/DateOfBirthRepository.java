package io.wizard.evotingapp.data.repository;

import io.wizard.evotingapp.data.DateOfBirth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DateOfBirthRepository extends MongoRepository<DateOfBirth, String> {
}
