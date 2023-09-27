package io.wizard.evotingapp.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DateOfBirth {
    @Id
    private String id;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
}
