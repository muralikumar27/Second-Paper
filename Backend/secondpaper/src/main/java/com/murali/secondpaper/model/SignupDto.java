package com.murali.secondpaper.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    @Size(min = 8, max = 16, message = "Enter password with valid length")
    private String password;
    @Email(message = "Please enter a valid email")
    private String email;
}
