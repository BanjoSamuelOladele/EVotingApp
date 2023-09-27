package io.wizard.evotingapp.data;

import io.wizard.evotingapp.data.enums.Region;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {
    @Id
    private String id;
    private String lga;
    private String state;
    private Region region;
}
