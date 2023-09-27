package io.wizard.evotingapp.party.services;

import io.wizard.evotingapp.exception.party.DuplicatePartyAcronymException;
import io.wizard.evotingapp.exception.party.DuplicatePartyNameException;
import io.wizard.evotingapp.party.dto.request.RegisterPartyRequest;
import io.wizard.evotingapp.party.dto.response.RegisterPartyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartyServiceImplTest {
    @Autowired
    private PartyServiceImpl partyService;
    @Test
    public void partyCanBeRegistered(){
        RegisterPartyRequest request =
                RegisterPartyRequest.builder()
                        .partyAcronym("Pn")
                        .partyName("partyName")
                        .build();
        RegisterPartyResponse response = partyService
                .registerParty(request);
        assertNotNull(response);
    }
    @Test
    public void registerPartyGetPartyIdAndAcronym(){
        RegisterPartyRequest request =
                RegisterPartyRequest.builder()
                        .partyAcronym("AP")
                        .partyName("anotherParty")
                        .build();
        RegisterPartyResponse response = partyService
                .registerParty(request);
        assertEquals(String.class, response.getPartyId().getClass());
        assertTrue(response.getPartyId().contains("evap/2023/"));
        assertEquals("AP", response.getPartyAcronym());
    }
    @Test
    public void partyNameCannotBeDuplicatedWhenRegistering(){
        RegisterPartyRequest request =
                RegisterPartyRequest.builder()
                        .partyAcronym("APY")
                        .partyName("anotherParty")
                        .build();
        assertThrows(DuplicatePartyNameException.class,
                ()-> partyService.registerParty(request));
    }
    @Test
    public void partyAcronymCannotBeDuplicatedWhenRegistering(){
        RegisterPartyRequest request =
                RegisterPartyRequest.builder()
                        .partyAcronym("ap")
                        .partyName("AuthorParty")
                        .build();
        assertThrows(DuplicatePartyAcronymException.class,
                ()-> partyService.registerParty(request));
    }
}