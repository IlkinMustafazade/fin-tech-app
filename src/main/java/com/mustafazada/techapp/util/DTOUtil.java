package com.mustafazada.techapp.util;

import com.mustafazada.techapp.dto.request.AuthenticationRequestDTO;
import com.mustafazada.techapp.dto.request.UserRequestDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.exception.InvalidDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DTOUtil {
    @Autowired
    private Logger logger;

    public void isValid(UserRequestDTO userRequestDTO) {
        logger.warn(userRequestDTO.toString());
        checkDTOInputInfo(userRequestDTO.getName());
        checkDTOInputInfo(userRequestDTO.getSurname());
        checkDTOInputInfo(userRequestDTO.getPassword());
        checkDTOInputInfo(userRequestDTO.getPin());
        checkDTOInputInfo(userRequestDTO.getAccountRequestDTOList());

    }

    public void isValid(AuthenticationRequestDTO authenticationRequestDTO) {
        logger.warn(authenticationRequestDTO.toString());
        checkDTOInputInfo(authenticationRequestDTO.getPassword());
        checkDTOInputInfo(authenticationRequestDTO.getPin());

    }

    private <T> void checkDTOInputInfo(T t) {
        if (Objects.isNull(t) || t.toString().isBlank()) {
            logger.error("Invalid Input");
            throw InvalidDTO.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.INVALID_DTO)
                            .message("Invalid Data")
                            .build()).build()).build();
        }
    }
}
