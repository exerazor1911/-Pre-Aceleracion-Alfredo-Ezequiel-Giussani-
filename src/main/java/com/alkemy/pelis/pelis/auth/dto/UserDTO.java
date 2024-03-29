package com.alkemy.pelis.pelis.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Setter
@Getter

@Data
public class UserDTO {
    @Email(message = "Username must be an email")
    private String username;
    @Size(min = 8)
    private String password;
}
