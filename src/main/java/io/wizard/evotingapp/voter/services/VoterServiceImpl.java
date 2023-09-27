package io.wizard.evotingapp.voter.services;
import io.wizard.evotingapp.data.Address;
import io.wizard.evotingapp.data.DateOfBirth;
import io.wizard.evotingapp.data.services.AddressServiceImpl;
import io.wizard.evotingapp.data.services.DateOfBirthImpl;
import io.wizard.evotingapp.exception.voter.DuplicatePhoneNumberException;
import io.wizard.evotingapp.exception.voter.ExistingVoterEmailException;
import io.wizard.evotingapp.exception.voter.InvalidLoginException;
import io.wizard.evotingapp.utils.HashPassword;
import io.wizard.evotingapp.utils.IdGenerator;
import io.wizard.evotingapp.utils.Mapper;
import io.wizard.evotingapp.voter.Voter;
import io.wizard.evotingapp.voter.dto.request.LoginVoterRequest;
import io.wizard.evotingapp.voter.dto.request.RegisterVoterRequest;
import io.wizard.evotingapp.voter.dto.responses.LoginRequestResponse;
import io.wizard.evotingapp.voter.dto.responses.RegisterVoterResponse;
import io.wizard.evotingapp.voter.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static io.wizard.evotingapp.utils.HashPassword.hashedPassword;
import static io.wizard.evotingapp.utils.Mapper.map;
import static io.wizard.evotingapp.utils.Mapper.reponse;

@Service
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService{
    private final VoterRepository voterRepository;
    private final AddressServiceImpl addressService;
    private final DateOfBirthImpl date;
    @Override
    public RegisterVoterResponse register(RegisterVoterRequest request) {
        checkForDuplicate(request);
        Address address = addressService.saveAddress(request);
        DateOfBirth dob = date.saveDateOfBirth(request);
        Voter voter = map(request, address, dob);
        voter.setPassword(hashedPassword(request.getPassword()));
        voter.setVoterId(generateVoterId());
        Voter savedVoter = voterRepository.save(voter);
        return map(savedVoter);
    }
    @Override
    public LoginRequestResponse login(LoginVoterRequest request) {
        Optional<Voter> foundVoter =
                voterRepository.findByEmail(request.getEmail());
        Voter voter = isConfirmPassword(request, foundVoter);
        return reponse(voter);
    }
    @Override
    public Voter findVoterByVoterId(String voterId) {
        Optional<Voter> foundVoter =
                voterRepository.findByVoterId(voterId);
        return foundVoter.get();
    }
    private static Voter isConfirmPassword(LoginVoterRequest request, Optional<Voter> foundVoter) {
        if (foundVoter.isEmpty()){
            throw new InvalidLoginException("Email or Password incorrect");
        }
        boolean confirmPassword = HashPassword.
                unHashPassword(request.getPassword(), foundVoter.get().getPassword());
        return setVoterStatus(confirmPassword, foundVoter);
    }
    private static Voter setVoterStatus(boolean passwordResult, Optional<Voter> foundVoter){
        Voter voter = foundVoter.get();
        if (passwordResult){
            voter.setLoggedIn(true);
        }
        return voter;
    }
    private void checkForDuplicate(RegisterVoterRequest request){
        boolean checkPhone = voterRepository.existsByPhoneNumber(request.getPhoneNumber());
        if (checkPhone){
            throw new DuplicatePhoneNumberException("Phone number already exists...");
        }
        boolean checkEmail = voterRepository.existsByEmail(request.getEmail());
        if (checkEmail){
            throw new ExistingVoterEmailException("email already exists...");
        }
    }
    private String generateVoterId(){
        String generatedId = IdGenerator.generateVotersId();
        boolean check = voterRepository.existsVoterByVoterId(generatedId);
        if (check){
            generateVoterId();
        }
        return generatedId;
    }
}
