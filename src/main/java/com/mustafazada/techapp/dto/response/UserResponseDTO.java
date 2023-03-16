package com.mustafazada.techapp.dto.response;

import com.mustafazada.techapp.dto.request.AccountRequestDTO;
import com.mustafazada.techapp.entity.TechUser;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String surname;
    private String password;
    private String pin;
    private String role;
    private List<AccountRequestDTO> accountRequestDTOList;

    public static UserResponseDTO entityResponse(TechUser user) {
        List<AccountRequestDTO> accounts = new ArrayList<>();
        user.getAccountList().forEach(accountDTO -> accounts.add(AccountRequestDTO.builder()
                .balance(accountDTO.getBalance())
                .currency(accountDTO.getCurrency())
                .isActive(accountDTO.getIsActive())
                .accountNo(accountDTO.getAccountNo()).build()));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .pin(user.getPin())
                .role(user.getRole())
                .accountRequestDTOList(accounts).build();
    }
}
