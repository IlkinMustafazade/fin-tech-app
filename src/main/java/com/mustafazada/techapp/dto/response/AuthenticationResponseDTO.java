package com.mustafazada.techapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthenticationResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tokenForUser;
}
