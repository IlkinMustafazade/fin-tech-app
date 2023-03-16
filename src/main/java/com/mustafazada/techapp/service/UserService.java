package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.request.UserRequestDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.dto.response.UserResponseDTO;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.exception.UserAlreadyExist;
import com.mustafazada.techapp.repository.UserRepository;
import com.mustafazada.techapp.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private DTOUtil dtoUtil;

    @Autowired
    private UserRepository userRepository;

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
                .password(userRequestDTO.getPassword())
                .pin(userRequestDTO.getPin())
                .role("ROLE_USER").build();

        user.addAccountToUser(userRequestDTO.getAccountRequestDTOList());

        return CommonResponseDTO.builder().status(Status.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("User Created").build())
                .data(UserResponseDTO.entityResponse(userRepository.save(user))).build();
    }
}
