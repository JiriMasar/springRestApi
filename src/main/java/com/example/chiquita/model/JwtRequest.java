package com.example.chiquita.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record JwtRequest(
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password
) {
}
