package com.sandbox.domain.oauth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    long kakao_id;
    String nickName;
    String accessToken;
    String refreshToken;

    public User(long kakao_id, String nickName, String accessToken, String refreshToken) {
        this.kakao_id = kakao_id;
        this.nickName = nickName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
