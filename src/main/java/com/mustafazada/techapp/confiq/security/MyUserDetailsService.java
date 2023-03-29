package com.mustafazada.techapp.confiq.security;

import com.mustafazada.techapp.dto.response.CommonResponseDTO;
import com.mustafazada.techapp.dto.response.Status;
import com.mustafazada.techapp.dto.response.StatusCode;
import com.mustafazada.techapp.entity.TechUser;
import com.mustafazada.techapp.exception.NoSuchUserExistException;
import com.mustafazada.techapp.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Logger logger;

    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        Optional<TechUser> byPin = userRepository.findByPin(pin);
        if (byPin.isPresent()) {
            return new MyUserDetails(byPin.get());
        } else {
            logger.error("There is no user with this pin: " + pin);
            throw NoSuchUserExistException.builder()
                    .responseDTO(CommonResponseDTO.builder()
                            .status(Status.builder().statusCode(StatusCode.USER_NOT_EXSIT)
                                    .message("There is no user whit this pin: " + pin)
                                    .build()).build()).build();
        }
    }
}
