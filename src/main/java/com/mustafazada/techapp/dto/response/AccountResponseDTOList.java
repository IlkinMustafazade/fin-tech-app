package com.mustafazada.techapp.dto.response;

import com.mustafazada.techapp.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AccountResponseDTOList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<AccountResponseDTO> accountResponseDTOLis;

    public static AccountResponseDTOList entityToDTO(List<Account> accountList) {
        List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
        accountList.forEach(account -> accountResponseDTOList.add(AccountResponseDTO.entityToDTO(account)));
        return AccountResponseDTOList.builder().accountResponseDTOLis(accountResponseDTOList).build();
    }
}
