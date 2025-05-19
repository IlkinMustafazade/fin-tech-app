package com.mustafazada.techapp.util;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.entity.Account;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.exception.InvalidTokenException;
import com.mustafazada.techapp.exception.NoTiedTokenException;
import com.mustafazada.techapp.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrentUser {
    UserRepository userRepository;

    public UserDetails getCurrentUser() {
        return (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public void validateCurrentUserAndAccount(Integer accountNo) {
        TechUser user = validateCurrentToken();
        List<Account> accountList = user.getAccountList();
        if (accountList.stream().noneMatch(account -> account.getAccountNo().equals(accountNo))) {
            throw NoTiedTokenException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.NOT_TIED_USER)
                            .message("The token is not tied to this user's account")
                            .build()).build()).build();
        }
    }

    private TechUser validateCurrentToken() {
        Optional<TechUser> userRepositoryByPin = userRepository.findByPin(getCurrentUser().getUsername());
        if (userRepositoryByPin.isEmpty()) {
            throw InvalidTokenException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.INVALID_TOKEN)
                            .message("Token is not valid")
                            .build()).build()).build();
        }

        return userRepositoryByPin.get();
    }
}

