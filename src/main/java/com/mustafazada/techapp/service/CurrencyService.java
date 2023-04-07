package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.mbdto.ValcursResponseDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.restclient.CbarRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private CbarRestClient cbarRestClient;

    public CommonResponseDTO<?> getCurrencyRate() {
        ValcursResponseDTO valcursResponseDTO = cbarRestClient.getCurrency();
        return CommonResponseDTO.builder().status(Status.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("All Currencies")
                        .build())
                .data(valcursResponseDTO).build();
    }
}
