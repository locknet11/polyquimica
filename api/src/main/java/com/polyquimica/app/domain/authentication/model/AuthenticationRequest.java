package com.polyquimica.app.domain.authentication.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @NotBlank(message = "auth.error.username.null")
    private String username;
    @NotBlank(message = "auth.error.password.null")
    private String password;
}
