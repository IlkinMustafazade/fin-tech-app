package com.mustafazada.techapp.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountToAccountRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer debitAccountNo;
    Integer creditAccountNo;
    BigDecimal amount;

}
