package io.wizard.evotingapp.party;

import io.wizard.evotingapp.data.enums.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder@Setter@Getter
@Document(collection = "candidate")
public class Candidate {
    @Id
    private String id;
    @Indexed(unique = true)
    private String candidateId;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String voter_Id;
    @Indexed(unique = true)
    private String partyAcronym;
    private String state;
    private Region region;
}
