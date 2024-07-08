package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public UserViewDTO register(UserCreationDTO userCreationDTO) throws EmailExistException {
        User user = userRepository.findByEmail(userCreationDTO.getEmail());
        if (user != null) {
            throw new EmailExistException("User with email " + userCreationDTO.getEmail() + " already exists");
        }

        user = new User(userCreationDTO.getFirstName(), userCreationDTO.getLastName(), userCreationDTO.getGender(), userCreationDTO.getEmail(), userCreationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));

        userRepository.save(user);
        return mapper.map(user, UserViewDTO.class);
    }
}
