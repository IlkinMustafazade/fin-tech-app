package com.mustafazada.techapp.dto.request;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthenticationRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;
    private String pin;

}
