package io.wizard.evotingapp.utils;

import io.wizard.evotingapp.data.Address;
import io.wizard.evotingapp.data.DateOfBirth;
import io.wizard.evotingapp.data.enums.Region;
import io.wizard.evotingapp.exception.voter.UnderEighteenException;
import io.wizard.evotingapp.party.Candidate;
import io.wizard.evotingapp.party.Party;
import io.wizard.evotingapp.party.dto.response.RegisterCandidateResponse;
import io.wizard.evotingapp.voter.Voter;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import io.wizard.evotingapp.voter.dto.responses.LoginRequestResponse;
import io.wizard.evotingapp.voter.dto.responses.RegisterVoterResponse;
import java.time.LocalDate;
import java.time.Period;
public class Mapper {
    public static Voter map(RegisterVoterRequest request, Address address, DateOfBirth dob) {
        return Voter.builder().lastName(request.getLastName())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(address)
                .dateOfBirth(dob)
                .age(calculateAge(request)).build();
    }
    public static DateOfBirth checkDateOfBirth(RegisterVoterRequest request) {
        calculateAge(request);
        DateOfBirth date = DateOfBirth.builder().build();
        date.setBirthDay(request.getDayOfBirth());
        date.setBirthMonth(request.getMonthOfBirth());
        date.setBirthYear(request.getYearOfBirth());
        return date;
    }
    private static String calculateAge(RegisterVoterRequest request){
        int year = Integer.parseInt(request.getYearOfBirth());
        int month = Integer.parseInt(request.getMonthOfBirth());
        int day = Integer.parseInt(request.getDayOfBirth());
        LocalDate numberOfYears = LocalDate.of(year, month, day);
        int age = Period.between(numberOfYears, LocalDate.now()).getYears();
        if (age < 18){
            throw new UnderEighteenException(request.getFirstName() + " Below 18 years...");
        }
        return String.valueOf(age);
    }
    public static Address createAddress(RegisterVoterRequest request) {
        Address address = Address.builder().build();
        address.setState(Region.getState(request.getState()));
        address.setRegion(Region.getRegion(request.getState()));
        address.setLga(request.getLga());
        return address;
    }
    public static RegisterVoterResponse map(Voter savedVoter){
        RegisterVoterResponse response = RegisterVoterResponse.builder().build();
        response.setVoterId(savedVoter.getVoterId());
        response.setMessage(savedVoter.getEmail()+
                " successfully saved...");
        return response;
    }
    public static LoginRequestResponse reponse(Voter voter){
        return LoginRequestResponse
                .builder()
                .isLoggedIn(voter.isLoggedIn())
                .message("successfully logged in...")
                .build();
    }
    public static Candidate map(Voter voter, Party party){
        return Candidate.builder().firstName(voter.getFirstName())
                .lastName(voter.getLastName()).voter_Id(voter.getVoterId())
                .email(voter.getEmail()).region(voter.getAddress().getRegion())
                .state(voter.getAddress().getState()).partyAcronym(party.getPartyAcronym())
                .build();
    }
    public static RegisterCandidateResponse map(Candidate savedCandidate, Party party){
        return RegisterCandidateResponse
                .builder()
                .candidateName(savedCandidate.getFirstName())
                .candidateId(savedCandidate.getCandidateId())
                .message(savedCandidate.getFirstName()
                        +" successfully registered with "+
                        party.getPartyName() +
                        " party")
                .build();
    }
}
