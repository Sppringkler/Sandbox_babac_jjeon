package com.sandbox.domain.oauth.controller;

import com.sandbox.domain.oauth.dto.AuthReq;
import com.sandbox.domain.oauth.dto.AuthResp;
import com.sandbox.domain.oauth.dto.MemberResp;
import com.sandbox.domain.oauth.exception.OAuthErrorResp;
import com.sandbox.domain.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final OAuthService os;

    @PostMapping("/auth")
    public ResponseEntity<AuthResp> auth(@RequestBody AuthReq req) {
        AuthResp resp = os.authenticate(req);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", resp.getRefreshToken())
                .httpOnly(true)    // JavaScript에서 접근 불가능
                .secure(true)      // HTTPS 전용
                .sameSite("none")
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(resp);
    }

    @GetMapping("/member")
    public ResponseEntity<MemberResp> getMember(@RequestHeader("Authorization") String accessToken) {
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            throw new OAuthErrorResp(400, "ERR_MISSING_ACCESS_TOKEN");
        }
        MemberResp resp = os.getMember(accessToken);

        return ResponseEntity.ok(resp);
    }

//    @GetMapping("/reissue")
//    public ResponseEntity<ReissueResp> getReissue(){
//
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(){
//
//    }
}
