package com.sandbox.domain.oauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoReq {
    private final String grant_type = "authorization_code";
    private final String client_id = "0da56a0700a56821782b91c49ce03b42";
    private final String redirect_uri = "https://ssafysandbox.vercel.app/oauth/redirect";
    private String code;

    public KakaoReq(String code) {
        this.code = code;
    }

    public MultiValueMap<String, String> toFormData() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grant_type);
        formData.add("client_id", client_id);
        formData.add("redirect_uri", redirect_uri);
        formData.add("code", code);
        return formData;
    }
}
