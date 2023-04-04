package com.mustafazada.techapp.entity;

import com.mustafazada.techapp.dto.request.AccountRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tech_user")
public class TechUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "password")
    private String password;
    @Column(name = "pin", unique = true)
    private String pin;
    @Column(name = "role")
    private String role;
    @OneToMany(cascade = CascadeType.PERSIST,
            mappedBy = "user")
    List<Account> accountList;

    public void addAccountToUser(List<AccountRequestDTO> accountRequestDTOList) {
        accountList = new ArrayList<>();
        accountRequestDTOList.forEach(accountDTO -> accountList.add(Account.builder()
                .balance(accountDTO.getBalance())
                .currency(accountDTO.getCurrency())
                .isActive(accountDTO.getIsActive())
                .accountNo(accountDTO.getAccountNo())
                .user(this)
                .build()));
    }
}
