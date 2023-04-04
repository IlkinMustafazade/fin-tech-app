package com.mustafazada.techapp.exception;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAlreadyExist extends RuntimeException {
    private final CommonResponseDTO<?> responseDTO;
}
