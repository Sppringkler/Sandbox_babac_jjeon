package com.sandbox.domain.smtp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResp {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
}
