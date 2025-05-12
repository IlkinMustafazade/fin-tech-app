package com.mustafazada.techapp.dto.response;


import com.mustafazada.techapp.entity.Account;
import com.mustafazada.techapp.exception.NotActiveAccountException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDTOList implements Serializable {
    private static final long serialVersionUID = 1L;

    List<AccountResponseDTO> accountResponseDTOList;

    public static AccountResponseDTOList entityToDTO(List<Account> accountList) {
        accountList = accountList.stream().filter(Account::getIsActive).collect(Collectors.toList());
        if (!accountList.isEmpty()) {
            List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
            accountList.forEach(account -> accountResponseDTOList.add(AccountResponseDTO.entityToDTO(account)));
            return AccountResponseDTOList.builder()
                    .accountResponseDTOList(accountResponseDTOList).build();
        } else {
            throw NotActiveAccountException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.NOT_ACTIVE_ACCOUNT)
                            .message("There is no active account")
                            .build()).build()).build();
        }

    }
}
