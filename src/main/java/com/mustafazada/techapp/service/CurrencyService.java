package com.mustafazada.techapp.service;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.dto.response.mbdto.ValCursResponseDTO;
import com.mustafazada.techapp.restclient.CbarRestClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyService {
    CbarRestClient cbarRestClient;

    public CommonResponseDTO<?> getCurrencies() {
        ValCursResponseDTO currency = cbarRestClient.getCurrency();
        return CommonResponseDTO.builder().status(Status.builder()
                .statusCode(StatusCode.SUCCESS)
                .message("All currencies have been successfully retrieved")
                .build()).data(currency).build();
    }
}
