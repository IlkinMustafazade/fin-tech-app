package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.response.AccountResponseDTOList;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.repository.UserRepository;
import com.mustafazada.techapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUser currentUser;

    public CommonResponseDTO<?> getAccount() {
        Optional<TechUser> user = userRepository.findByPin(currentUser.getCurrentUser().getUsername());

        return CommonResponseDTO.builder()
                .data(AccountResponseDTOList.entityToDTO(user.get().getAccountList()))
                .status(Status.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("Accounts Successfully fetched!")
                        .build()).build();
    }
}
