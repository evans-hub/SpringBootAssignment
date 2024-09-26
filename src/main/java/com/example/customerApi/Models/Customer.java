package com.example.customerApi.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Long id;

                @NotBlank
                private String firstName;

                @NotBlank
                private String lastName;

                @NotBlank
                @Email
                private String email;

                @NotBlank
                private String phoneNumber;

                private BigDecimal accountBalance;
        }




