package com.sandbox.domain.oauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoRenewReq {
    private final String grant_type = "refresh_code";
    private final String client_id = "0da56a0700a56821782b91c49ce03b42";
    private String refreshToken;

    public KakaoRenewReq(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public MultiValueMap<String, String> toFormData() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grant_type);
        formData.add("client_id", client_id);
        formData.add("refresh_token", refreshToken);
        return formData;
    }
}
