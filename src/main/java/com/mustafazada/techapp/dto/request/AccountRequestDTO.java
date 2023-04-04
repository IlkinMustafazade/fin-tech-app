package com.mustafazada.techapp.dto.request;

import com.mustafazada.techapp.util.Currency;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AccountRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal balance;
    private Currency currency;
    private Boolean isActive;
    private Integer accountNo;
}
