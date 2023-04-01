package com.mustafazada.techapp.controller;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<?> account() {
        return new ResponseEntity<>(accountService.getAccount(), HttpStatus.OK);
    }
}
