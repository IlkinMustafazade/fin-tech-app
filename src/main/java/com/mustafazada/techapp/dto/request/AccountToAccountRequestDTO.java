package com.mustafazada.techapp.dto.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AccountToAccountRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer debitAccount;
    private Integer creditAccount;
    private BigDecimal amount;
}
