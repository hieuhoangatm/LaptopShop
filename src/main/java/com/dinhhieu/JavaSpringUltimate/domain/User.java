package com.dinhhieu.JavaSpringUltimate.domain;

import com.dinhhieu.JavaSpringUltimate.service.validator.StrongPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Size(min = 3, message = "fullname must be have least 3 characters")
    private String fullName;

    @NotNull
    @Size(min=6,message = "password must be have 2 characters")
    @StrongPassword(message = "password must be have 9=8 characters")
    private String password;

    private String address;
    private String phone;
    private String avatar;

    //user has one role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    List<Order> orders;
}
