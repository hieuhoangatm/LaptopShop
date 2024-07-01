package com.dinhhieu.JavaSpringUltimate.DTO;

import com.dinhhieu.JavaSpringUltimate.service.validator.RegisterChecked;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@RegisterChecked
public class RegisterDTO {
    @NotNull
    @Size(min =3,message = "Firstname must be have least 3 character")
    private String firstName;

    private String lastName;

    @NotNull
    @Email(message = "Email is not valid or empty", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;


    private String password;

    @NotNull
    @Size(min = 3,message = "confirmPassword must be have 3 character")
    private String confirmPassword;


}
