package com.sandbox.domain.oauth.service;

import com.sandbox.domain.oauth.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final WebClient webClient;
    private final String Content_Type = "application/x-www-form-urlencoded;charset=utf-8";

    public KakaoResp getAccessToken(KakaoReq req){
         return webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header("Content-Type",Content_Type)
                 .body(BodyInserters.fromFormData(req.toFormData()))
                .retrieve()
                .bodyToMono(KakaoResp.class)
                .block();
    }

    public UserInfoResp getUserInfo(String accessToken){
        return webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(UserInfoResp.class)
                .block();
    }

    public KakaoRenewResp getRenewAccessToken(KakaoRenewReq req){
        return webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header("Content-Type",Content_Type)
                .body(BodyInserters.fromFormData(req.toFormData()))
                .retrieve()
                .bodyToMono(KakaoRenewResp.class)
                .block();
    }
}
