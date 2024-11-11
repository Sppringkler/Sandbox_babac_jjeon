package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;

public interface SMTPService {
    EmailResp sendSecretNumber(EmailReq req);
    AuthenticationResp authenticate(AuthenticationReq req);
    String makeSecretNumber();
}
