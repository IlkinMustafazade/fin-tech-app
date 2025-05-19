package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.response.AccountResponseDTOList;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.repositories.UserRepository;
import com.mustafazada.techapp.util.CurrentUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    UserRepository userRepository;
    CurrentUser currentUser;

    public CommonResponseDTO<?> getAccountsForUser() {
        Optional<TechUser> user = userRepository.findByPin(currentUser.getCurrentUser().getUsername());
        return CommonResponseDTO.builder().status(Status.builder()
                .statusCode(StatusCode.SUCCESS)
                .message("Account(s) for user: "
                        + currentUser.getCurrentUser().getUsername()
                        + " was successfully fetched")
                .build()).data(AccountResponseDTOList.entityToDTO(user.get().getAccountList())).build();

    }
}
