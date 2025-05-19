package com.mustafazada.techapp.restclient;

import com.mustafazada.techapp.config.ApplicationConfig;
import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.dto.response.mbdto.ValCursResponseDTO;
import com.mustafazada.techapp.exception.CbarRestException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CbarRestClient {
    RestTemplate restTemplate;

    public ValCursResponseDTO getCurrency() {
        ValCursResponseDTO responseDTO;
        try {
            responseDTO = restTemplate.getForObject(ApplicationConfig.urlMB, ValCursResponseDTO.class);
            if (Objects.isNull(responseDTO)) {
                throw CbarRestException.builder().responseDTO(CommonResponseDTO.builder()
                        .status(Status.builder()
                                .statusCode(StatusCode.CBAR_ERROR)
                                .message("Error happened while getting currency from cbar")
                                .build()).build()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw CbarRestException.builder().responseDTO(CommonResponseDTO.builder()
                    .status(Status.builder()
                            .statusCode(StatusCode.CBAR_ERROR)
                            .message("Error happened while getting currency from cbar")
                            .build()).build()).build();
        }
        return responseDTO;
    }
}
