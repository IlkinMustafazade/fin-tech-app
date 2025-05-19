package com.mustafazada.techapp.controller;

import com.mustafazada.techapp.dto.request.AccountToAccountRequestDTO;
import com.mustafazada.techapp.service.AccountService;
import com.mustafazada.techapp.service.TransferMoneyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    TransferMoneyService transferMoneyService;
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        return new ResponseEntity<>(accountService.getAccountsForUser(), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestBody AccountToAccountRequestDTO requestDTO) {
        return new ResponseEntity<>(transferMoneyService.account2account(requestDTO), HttpStatus.OK);
    }
}
