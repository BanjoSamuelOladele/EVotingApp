package io.wizard.evotingapp.service;
import io.wizard.evotingapp.EVotingAppApplication;
import io.wizard.evotingapp.exception.enums.InvalidStateException;
import io.wizard.evotingapp.exception.voter.DuplicatePhoneNumberException;
import io.wizard.evotingapp.exception.voter.ExistingVoterEmailException;
import io.wizard.evotingapp.exception.voter.InvalidLoginException;
import io.wizard.evotingapp.exception.voter.UnderEighteenException;
import io.wizard.evotingapp.voter.dto.request.LoginVoterRequest;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import io.wizard.evotingapp.voter.dto.responses.LoginRequestResponse;
import io.wizard.evotingapp.voter.dto.responses.RegisterVoterResponse;
import io.wizard.evotingapp.voter.services.VoterServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EVotingAppApplication.class)
public class VoterServicesTest {
    @Autowired
    private VoterServiceImpl voterService;
    @Test
    public void registerVoter(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre@gmail.com").password("password")
                .phoneNumber("09086750905").firstName("firstName").lastName("lastName")
                .yearOfBirth("2003").monthOfBirth("11").dayOfBirth("11").state("lagos").build();
        RegisterVoterResponse response = voterService.register(request);
        assertNotNull(response);
    }
    @Test
    public void registerVoterGetId(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre1@gmail.com").password("password")
                .phoneNumber("09086750904").firstName("firstName").lastName("lastName")
                .yearOfBirth("2004").monthOfBirth("11").dayOfBirth("16").state("lagos").build();
        RegisterVoterResponse response = voterService.register(request);
        assertNotNull(response.getVoterId());
        assertTrue(response.getVoterId().contains("evav/2023/"));
    }
    @Test
    public void noDuplicateVoter(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre1@gmail.com").password("password")
                .phoneNumber("09086750906").firstName("firstName").lastName("lastName")
                .yearOfBirth("2004").monthOfBirth("11").dayOfBirth("16").state("lagos").build();
        assertThrows(ExistingVoterEmailException.class,
                ()->voterService.register(request));
    }
    @Test
    public void userCannotBeLessThanEighteen(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre11@gmail.com").password("password")
                .phoneNumber("09086750909").firstName("firstName").lastName("lastName")
                .yearOfBirth("2005").monthOfBirth("11").dayOfBirth("16").state("lagos").build();
        assertThrows(UnderEighteenException.class,
                ()-> voterService.register(request));
    }
    @Test
    public void mobileNumberCannotBeDuplicate(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre1@gmail121.com").password("password")
                .phoneNumber("09086750906").firstName("firstName").lastName("lastName")
                .yearOfBirth("2004").monthOfBirth("11").dayOfBirth("16").state("lagos").build();
        assertThrows(DuplicatePhoneNumberException.class,
                ()-> voterService.register(request));
    }
    @Test
    public void voterCannotInputAnInValidStateDuringRegistration(){
        RegisterVoterRequest request = RegisterVoterRequest
                .builder().email("leumasre1@gmail12156.com").password("password")
                .phoneNumber("09086750978").firstName("firstName").lastName("lastName")
                .yearOfBirth("2004").monthOfBirth("11").dayOfBirth("16").state("lag").build();
        assertThrows(InvalidStateException.class,
                ()-> voterService.register(request));
    }
    @Test
    public void voterCanLogInTOAccount(){
        LoginVoterRequest request = LoginVoterRequest.builder()
                .email("leumasre1@gmail.com").password("password")
                .build();
        LoginRequestResponse response = voterService.login(request);
        assertTrue(response.isLoggedIn());
    }
    @Test
    public void voterCannotLogInWithAnIncorrectLogInDetails(){
        LoginVoterRequest request = LoginVoterRequest.builder()
                .email("leumasre1@gmail1215634.com").password("password")
                .build();
        assertThrows(InvalidLoginException.class, ()->
                voterService.login(request));
        try{
            voterService.login(request);
        }catch (Exception exception){
            assertEquals(InvalidLoginException.class, exception.getClass());
            assertEquals("Email or Password incorrect", exception.getMessage());
        }
    }
    @Test
    public void voterCannotLogInWithAnIncorrectLogInPassword(){
        LoginVoterRequest request = LoginVoterRequest.builder()
                .email("leumasre1@gmail.com").password("Password")
                .build();
        assertThrows(InvalidLoginException.class, ()->
                voterService.login(request));
        try{
            voterService.login(request);
        }catch (Exception exception){
            assertEquals(InvalidLoginException.class, exception.getClass());
            assertEquals("Email or Password incorrect", exception.getMessage());
        }
    }

}
