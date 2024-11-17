package com.sandbox.domain.oauth.service;

import com.sandbox.domain.oauth.dto.*;
import com.sandbox.domain.oauth.entity.User;
import com.sandbox.domain.oauth.repository.OAuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthServiceImpl implements OAuthService {
    private final OAuthRepository or;
    private final KakaoService kakaoService;

    @Override
    public AuthResp authenticate(AuthReq req) {
        KakaoResp kakaoResp = kakaoService.getAccessToken(new KakaoReq(req.getCode()));

        System.out.println("============================================");
        System.out.println(kakaoResp.getAccess_token());

        // 닉네임 조회
        UserInfoResp userInfoResp = kakaoService.getUserInfo(kakaoResp.getAccess_token());
        // 회원 저장

        User user = new User(userInfoResp.getId(), userInfoResp.getProperties().get("kakao_account.email")
        , userInfoResp.getProperties().get("kakao_account.name")
        , kakaoResp.getAccess_token()
        , kakaoResp.getRefresh_token());

        or.save(user);

        return new AuthResp(kakaoResp.getAccess_token(), kakaoResp.getRefresh_token());
    }

    @Override
    public MemberResp getMember(String accessToken) {
        UserInfoResp userInfoResp = kakaoService.getUserInfo(accessToken);

        return new MemberResp(userInfoResp.getProperties().get("kakao_account.name"));
    }

    @Override
    public ReissueResp getReissue(String refreshToken) {
        return null;
    }

    @Override
    public boolean logout(String refreshToken) {
        return false;
    }
}
