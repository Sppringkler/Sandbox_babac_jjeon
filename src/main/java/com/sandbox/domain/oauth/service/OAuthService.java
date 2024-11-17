package com.sandbox.domain.oauth.service;

import com.sandbox.domain.oauth.dto.AuthReq;
import com.sandbox.domain.oauth.dto.AuthResp;
import com.sandbox.domain.oauth.dto.MemberResp;
import com.sandbox.domain.oauth.dto.ReissueResp;

public interface OAuthService {
    AuthResp authenticate(AuthReq req);
    MemberResp getMember(String accessToken);
    ReissueResp getReissue(String refreshToken);
    boolean logout(String refreshToken);
}
