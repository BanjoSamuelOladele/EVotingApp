package io.wizard.evotingapp.election;

import io.wizard.evotingapp.party.Party;
import io.wizard.evotingapp.voter.Voter;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter@Setter@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "election")
public class Election {
    @Id
    private String id;
    private String electionId;
    private String electionName;
    private String electionType;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private boolean isRunning = false;
    @DBRef
    private List<Voter> voterList;
    @DBRef
    private List<Party> partyList;
}
