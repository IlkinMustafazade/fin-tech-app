package com.mustafazada.techapp.restclient;

import com.mustafazada.techapp.confiq.ApplicationConfig;
import com.mustafazada.techapp.dto.mbdto.ValcursResponseDTO;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.exception.InvalidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CbarRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public ValcursResponseDTO getCurrency() {
        ValcursResponseDTO valcursResponseDTO;
        try {
            System.out.println(ApplicationConfig.urlMB);
            valcursResponseDTO = restTemplate.getForObject(ApplicationConfig.urlMB, ValcursResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw InvalidDTO.builder()
                    .responseDTO(CommonResponseDTO.builder()
                            .status(Status.builder()
                                    .statusCode(StatusCode.CBAR_ERROR)
                                    .message("Error happened while getting response from CBAR")
                                    .build()).build()).build();
        }
        if (Objects.isNull(valcursResponseDTO)) {
            throw InvalidDTO.builder()
                    .responseDTO(CommonResponseDTO.builder()
                            .status(Status.builder()
                                    .statusCode(StatusCode.CBAR_ERROR)
                                    .message("Error happened while getting response from CBAR")
                                    .build()).build()).build();
        }

        return valcursResponseDTO;
    }
}
