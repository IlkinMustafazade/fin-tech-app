package com.mustafazada.techapp.controller;


import com.mustafazada.techapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/currency")
    public ResponseEntity<?> currencyFromMB() {
        return new ResponseEntity<>(currencyService.getCurrencyRate(), HttpStatus.OK);
    }
}
