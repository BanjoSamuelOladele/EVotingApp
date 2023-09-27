package io.wizard.evotingapp.party.services;

import io.wizard.evotingapp.party.dto.response.RegisterCandidateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CandidateServiceImplTest {

    @Autowired
    private CandidateServiceImpl candidateService;
    @Test
    public void registerCandidate(){
        RegisterCandidateResponse response =
                candidateService.registerCandidate("evav/2023/Es3iUYM", "Pn");
        assertNotNull(response);
    }
    @Test
    public void registerCandidateGetId(){
        RegisterCandidateResponse response =
                candidateService.registerCandidate("evav/2023/9u9FJax", "Pn");
        assertEquals("firstName", response.getCandidateName());
        assertTrue(response.getMessage().contains("partyName"));
        assertTrue(response.getCandidateId().contains("evap/PN/2023"));
    }
    @Test
    public void candidateCannotRegisterIfNotRegisteredAsVoter(){
//        assertThrows(,()->candidateService
//                .registerCandidate("evav/2023/999",
//                        "Pn"));
    }
}