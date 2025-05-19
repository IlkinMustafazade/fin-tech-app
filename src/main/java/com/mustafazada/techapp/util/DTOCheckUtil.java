package com.mustafazada.techapp.util;

import com.mustafazada.techapp.dto.request.AccountToAccountRequestDTO;
import com.mustafazada.techapp.dto.request.AuthenticationRequestDTO;
import com.mustafazada.techapp.dto.request.UserRequestDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.exception.InvalidDTOException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DTOCheckUtil {
    Logger logger;

    public void isValid(UserRequestDTO userRequestDTO) {
        checkDTOInput(userRequestDTO.getName());
        checkDTOInput(userRequestDTO.getSurname());
        checkDTOInput(userRequestDTO.getPassword());
        checkDTOInput(userRequestDTO.getPin());
        checkDTOInput(userRequestDTO.getAccountRequestDTOList());
    }

    public void isValid(AuthenticationRequestDTO authenticationRequestDTO) {
        checkDTOInput(authenticationRequestDTO.getPin());
        checkDTOInput(authenticationRequestDTO.getPassword());
    }

    public void isValid(AccountToAccountRequestDTO requestDTO) {
        checkDTOInput(requestDTO.getDebitAccountNo());
        checkDTOInput(requestDTO.getCreditAccountNo());
        checkDTOInput(requestDTO.getAmount());
    }

    private <T> void checkDTOInput(T t) {
        if (Objects.isNull(t) || t.toString().isBlank()) {
            logger.error("DTO input is null or empty");
            throw InvalidDTOException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.INVALID_DTO)
                            .message("DTO input is null or empty")
                            .build()).build()).build();
        }
    }
}
