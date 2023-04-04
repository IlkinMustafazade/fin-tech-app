package com.mustafazada.techapp.controller;

import com.mustafazada.techapp.dto.request.AuthenticationRequestDTO;
import com.mustafazada.techapp.dto.request.UserRequestDTO;
import com.mustafazada.techapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.saveUser(userRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return new ResponseEntity<>(userService.loginUser(authenticationRequestDTO), HttpStatus.OK);
    }
}
