package com.mustafazada.techapp.dto.response;

import com.mustafazada.techapp.entity.Account;
import com.mustafazada.techapp.util.Currency;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AccountResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal balance;
    private Currency currency;
    private Boolean isActive;
    private Integer accountNo;

    public static AccountResponseDTO entityToDTO(Account account) {
        return AccountResponseDTO.builder()
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .isActive(account.getIsActive())
                .accountNo(account.getAccountNo())
                .build();
    }
}
