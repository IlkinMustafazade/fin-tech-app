package com.mustafazada.techapp.service;

import com.mustafazada.techapp.confiq.security.JwtUtil;
import com.mustafazada.techapp.dto.request.AuthenticationRequestDTO;
import com.mustafazada.techapp.dto.request.UserRequestDTO;
import com.mustafazada.techapp.dto.response.*;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.exception.NoSuchUserExistException;
import com.mustafazada.techapp.exception.UserAlreadyExist;
import com.mustafazada.techapp.repository.UserRepository;
import com.mustafazada.techapp.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private DTOUtil dtoUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public CommonResponseDTO<?> saveUser(UserRequestDTO userRequestDTO) {
        dtoUtil.isValid(userRequestDTO);

        if (userRepository.findByPin(userRequestDTO.getPin()).isPresent()) {
            throw new UserAlreadyExist(CommonResponseDTO.builder().status(Status.builder()
                    .statusCode(StatusCode.USER_EXIST)
                    .message("User already exist")
                    .build()).build());
        }

        TechUser user = TechUser.builder()
                .name(userRequestDTO.getName())
                .surname(userRequestDTO.getSurname())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .pin(userRequestDTO.getPin())
                .role("ROLE_USER").build();

        user.addAccountToUser(userRequestDTO.getAccountRequestDTOList());

        return CommonResponseDTO.builder().status(Status.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("User Created").build())
                .data(UserResponseDTO.entityResponse(userRepository.save(user))).build();
    }


    public CommonResponseDTO<?> loginUser(AuthenticationRequestDTO authenticationRequestDTO) {
        dtoUtil.isValid(authenticationRequestDTO);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (
                            authenticationRequestDTO.getPin(),
                            authenticationRequestDTO.getPassword()
                    ));

        } catch (Exception e) {
            throw NoSuchUserExistException.builder()
                    .responseDTO(CommonResponseDTO.builder()
                            .status(Status.builder().statusCode(StatusCode.USER_NOT_EXSIT)
                                    .message("There is no user whit this pin: " + authenticationRequestDTO.getPin())
                                    .build()).build()).build();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getPin());

        return CommonResponseDTO.builder()
                .data(AuthenticationResponseDTO.builder().tokenForUser(jwtUtil.createToken(userDetails))
                        .build())
                .status(Status.builder().statusCode(StatusCode.SUCCESS).message("Token was created successfully")
                        .build()).build();
    }
}
