package com.mustafazada.techapp.util;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.exception.EqualsAccountException;
import com.mustafazada.techapp.exception.InvalidAmountException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountCheckUtil {
    public void checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw InvalidAmountException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.INVALID_AMOUNT)
                            .message("Amount must be greater than 0")
                            .build()).build()).build();
        }
    }

    public void checkEqualityAccounts(Integer debitAccountNo, Integer creditAccountNo) {
        if (debitAccountNo.equals(creditAccountNo)) {
            throw EqualsAccountException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.EQUALS_ACCOUNT)
                            .message("Debit account: " + debitAccountNo
                                    + " and credit account: " + creditAccountNo
                                    + " are the same")
                            .build()).build()).build();
        }
    }
}
