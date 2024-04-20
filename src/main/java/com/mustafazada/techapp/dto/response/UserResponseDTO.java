package com.mustafazada.techapp.dto.response;

import com.mustafazada.techapp.dto.request.AccountRequestDTO;
import com.mustafazada.techapp.entity.TechUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;
    String name;
    String surname;
    String password;
    String pin;
    String role;
    List<AccountRequestDTO> accountRequestDTOList;

    public static UserResponseDTO entityResponse(TechUser user) {
        List<AccountRequestDTO> accountList = new ArrayList<>();
        user.getAccountList().forEach(account -> accountList.add(AccountRequestDTO.builder()
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .isActive(account.getIsActive())
                .accountNo(account.getAccountNo()).build()));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .pin(user.getPin())
                .role(user.getRole())
                .accountRequestDTOList(accountList).build();
    }
}
