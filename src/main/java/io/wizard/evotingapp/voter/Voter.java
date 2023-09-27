package io.wizard.evotingapp.voter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.wizard.evotingapp.data.Address;
import io.wizard.evotingapp.data.DateOfBirth;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Builder@Getter@Setter
@Document(collection = "Voters")
public class Voter {
    @Id
    @JsonIgnore
    private String  id;
    @Field(order = 2)
    @Indexed(unique = true)
    private String voterId;
    private String lastName;
    private String firstName;
    @Field(name = "email", order = 1)
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private String password;
    private String age;
    private boolean isLoggedIn = false;
    @DBRef
    private Address address;
    @DBRef
    private DateOfBirth dateOfBirth;
}
