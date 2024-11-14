package com.sandbox.domain.smtp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EmailAuthentication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String email;
    String authentication;

    public EmailAuthentication(String email, String authentication) {
        this.email = email;
        this.authentication = authentication;
    }
}
