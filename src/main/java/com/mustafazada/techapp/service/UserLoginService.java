package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.request.AuthenticationRequestDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.exception.BadInputCredentialsException;
import com.mustafazada.techapp.util.DTOCheckUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserLoginService {
    AuthenticationManager authenticationManager;
    DTOCheckUtil dtoCheckUtil;

    public CommonResponseDTO<?> loginUser(AuthenticationRequestDTO authenticationRequestDTO) {
        dtoCheckUtil.isValid(authenticationRequestDTO);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequestDTO.getPin(),
                    authenticationRequestDTO.getPassword()
            ));
        }catch (Exception e){
            throw BadInputCredentialsException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.BAD_CREDENTIALS)
                            .message("The Credential is incorrect. Pin: "
                                    + authenticationRequestDTO.getPin() + " or Password: "
                                    + authenticationRequestDTO.getPassword() + " is wrong")
                    .build()).build()).build();
        }


        return CommonResponseDTO.builder().status(Status.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("Welcome to FIN_TECH application")
                .build()).data(authenticationRequestDTO).build();
    }
}
