package io.wizard.evotingapp.party;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder@Getter@Setter
public class Party {
    private String id;
    @Indexed(unique = true)
    private String partyId;
    @Indexed(unique = true)
    private String partyName;
    @Indexed(unique = true)
    private String partyAcronym;
    private List<Candidate> candidates;
}
