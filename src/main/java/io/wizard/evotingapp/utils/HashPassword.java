package io.wizard.evotingapp.utils;

import io.wizard.evotingapp.exception.voter.InvalidLoginException;
import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    public static String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean unHashPassword(String requestPassword, String hashedPassword) {
        boolean check = BCrypt.checkpw(requestPassword, hashedPassword);
        if (!check){
            throw new InvalidLoginException("Email or Password incorrect");
        }
        return check;
    }
}
