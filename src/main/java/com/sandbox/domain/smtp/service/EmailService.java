package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;

public interface EmailService {
    EmailResp sendEmail(EmailReq emailReq);
    EmailResp verifyAuthenticationCode(String email, String code);
}
