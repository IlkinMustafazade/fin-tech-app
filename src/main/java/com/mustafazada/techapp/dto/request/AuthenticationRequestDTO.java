package com.mustafazada.techapp.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    String pin;
    String password;
}
