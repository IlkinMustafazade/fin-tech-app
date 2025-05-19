package com.mustafazada.techapp.exception;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsufficientBalanceException extends RuntimeException {
    CommonResponseDTO<?> responseDTO;
}
