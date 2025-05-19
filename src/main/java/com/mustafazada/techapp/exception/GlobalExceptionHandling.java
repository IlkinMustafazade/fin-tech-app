package com.mustafazada.techapp.exception;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> internalError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>
                (
                        CommonResponseDTO.builder().status(Status.builder()
                                .statusCode(StatusCode.INTERNAL_ERROR)
                                .message("Internal Error")
                                .build()).build(), HttpStatus.INTERNAL_SERVER_ERROR
                );
    }

    @ExceptionHandler(value = InvalidDTOException.class)
    public ResponseEntity<?> invalidDTO(InvalidDTOException invalidDTOException) {
        return new ResponseEntity<>(invalidDTOException.getResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<?> userExist(UserAlreadyExistException userAlreadyExistException) {
        return new ResponseEntity<>(userAlreadyExistException.getResponseDTO(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = AccountAlreadyExistException.class)
    public ResponseEntity<?> accountExist(AccountAlreadyExistException accountAlreadyExistException) {
        return new ResponseEntity<>(accountAlreadyExistException.getResponseDTO(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoSuchUserExistException.class)
    public ResponseEntity<?> userNotExist(NoSuchUserExistException noSuchUserExistException) {
        return new ResponseEntity<>(noSuchUserExistException.getResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadInputCredentialsException.class)
    public ResponseEntity<?> badCredentials(BadInputCredentialsException badInputCredentialsException) {
        return new ResponseEntity<>(badInputCredentialsException.getResponseDTO(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NotActiveAccountException.class)
    public ResponseEntity<?> notActiveAccount(NotActiveAccountException notActiveAccountException) {
        return new ResponseEntity<>(notActiveAccountException.getResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidAmountException.class)
    public ResponseEntity<?> invalidAmount(InvalidAmountException invalidAmountException) {
        return new ResponseEntity<>(invalidAmountException.getResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EqualsAccountException.class)
    public ResponseEntity<?> sameAccounts(EqualsAccountException equalsAccountException) {
        return new ResponseEntity<>(equalsAccountException.getResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<?> insufficientBalance(InsufficientBalanceException insufficientBalanceException) {
        return new ResponseEntity<>(insufficientBalanceException.getResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotExistAccountException.class)
    public ResponseEntity<?> insufficientBalance(NotExistAccountException notExistAccountException) {
        return new ResponseEntity<>(notExistAccountException.getResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<?> insufficientBalance(InvalidTokenException invalidTokenException) {
        return new ResponseEntity<>(invalidTokenException.getResponseDTO(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NoTiedTokenException.class)
    public ResponseEntity<?> insufficientBalance(NoTiedTokenException noTiedTokenException) {
        return new ResponseEntity<>(noTiedTokenException.getResponseDTO(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CbarRestException.class)
    public ResponseEntity<?> insufficientBalance(CbarRestException cbarRestException) {
        return new ResponseEntity<>(cbarRestException.getResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
