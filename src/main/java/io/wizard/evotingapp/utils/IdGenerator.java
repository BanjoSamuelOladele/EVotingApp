package io.wizard.evotingapp.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
public class IdGenerator {
    private static final String RANDOM_VALUES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9876543210abcdefghijklmnopqrstuvwxyz";
    public static String generateVotersId(){
        StringBuilder builder = new StringBuilder("evav/");
        return generator(builder);
    }
    private static String generator(StringBuilder builder) {
        SecureRandom random = new SecureRandom();
        builder.append(LocalDate.now().getYear()).append("/");
        for (int index = 0; index < 7; index++) {
            builder.append(RANDOM_VALUES
                    .charAt(random
                            .nextInt(0, RANDOM_VALUES.length())));
        }
        return builder.toString();
    }
    public static String generatePartyId() {
        StringBuilder builder = new StringBuilder("evap/");
        return generator(builder);
    }
    public static String generateCandidateId(String partyAcronym) {
        StringBuilder builder = new StringBuilder("evap/");
        builder.append(partyAcronym.toUpperCase()).append("/");
        return generator(builder);
    }
}
