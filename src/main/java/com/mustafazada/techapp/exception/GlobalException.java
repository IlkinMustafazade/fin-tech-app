package com.mustafazada.techapp.exception;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> internalError() {
        return new ResponseEntity<>(CommonResponseDTO.builder().status(Status.builder()
                .statusCode(StatusCode.INTERNAL_ERROR)
                .message("Internal Error").build()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidDTO.class)
    public ResponseEntity<?> invalidDto(InvalidDTO invalidDTO) {
        return new ResponseEntity<>(invalidDTO.getResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<?> userExist(UserAlreadyExist userAlreadyExist) {
        return new ResponseEntity<>(userAlreadyExist.getResponseDTO(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoSuchUserExistException.class)
    public ResponseEntity<?> notFoundUser(NoSuchUserExistException noSuchUserExistException) {
        return new ResponseEntity<>(noSuchUserExistException.getResponseDTO(), HttpStatus.NOT_FOUND);
    }
}
