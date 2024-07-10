package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.inputdto.UserEditDTO;
import com.social.socialapi.dto.outputdto.CloudinaryResponseDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UsernameExistException;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.FileUploadService;
import com.social.socialapi.service.SendEmailService;
import com.social.socialapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    private final SendEmailService sendEmailService;

    private final FileUploadService fileUploadService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, ModelMapper mapper, SendEmailService sendEmailService, FileUploadService fileUploadService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.sendEmailService = sendEmailService;
        this.fileUploadService = fileUploadService;
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
    public UserViewDTO getUserDTOByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapper.map(user, UserViewDTO.class);
    }

    @Override
    public UserViewDTO getUserViewDTOById(int userId) {
        User user = userRepository.findById(userId);
        return mapper.map(user, UserViewDTO.class);
    }

    @Override
    @Transactional
    public UserViewDTO editUser(UserEditDTO userEditDTO) {
        User user = userRepository.findById(userEditDTO.getId());
        user.setFirstName(userEditDTO.getFirstName());
        user.setLastName(userEditDTO.getLastName());
        user.setBio(userEditDTO.getBio());
        user.setGender(userEditDTO.getGender());

        if (userEditDTO.getFile() != null) {
            CloudinaryResponseDTO cloudinaryResponse = fileUploadService.uploadFile(userEditDTO.getFile(), userEditDTO.getFile().getOriginalFilename());
            user.setProfile_pic_url(cloudinaryResponse.getUrl());
        }

        userRepository.save(user);
        return mapper.map(user, UserViewDTO.class);
    }


    @Override
    public UserViewDTO register(UserCreationDTO userCreationDTO) throws EmailExistException, UsernameExistException {
        User user = userRepository.findByEmail(userCreationDTO.getEmail());
        if (user != null) {
            throw new EmailExistException("User with email " + userCreationDTO.getEmail() + " already exists");
        }

        if (userRepository.existsByUsername(userCreationDTO.getUsername())) {
            throw new UsernameExistException("User with username exists");
        }

        user = new User(userCreationDTO.getFirstName(), userCreationDTO.getLastName(), userCreationDTO.getGender(), userCreationDTO.getEmail(), userCreationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));

        userRepository.save(user);
        String emailContent = "Register";
        sendEmailService.sendEmail(user.getEmail(), emailContent , "Vui lòng xác thực email của" +
                " tài khoản "+ user.getUsername());
        return mapper.map(user, UserViewDTO.class);
    }


}
