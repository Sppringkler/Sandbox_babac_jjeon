package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.exception.ErrorResp;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMTPServiceImpl implements SMTPService {
    private static final Map<String,String> emailAuthenticationMap = new HashMap<>();
    private final JavaMailSender jms;

    @Override
    public EmailResp sendSecretNumber(EmailReq req) {
        try {
            String secretNum = makeSecretNumber();
            emailAuthenticationMap.put(req.getEmail(), secretNum);
            return new EmailResp(true);
        } catch (Exception e) {
            throw new ErrorResp("요청이 정상적으로 처리되지 않았습니다.");
        }
    }

    @Override
    public AuthenticationResp authenticate(AuthenticationReq req) {
        try {
            String email = req.getEmail();
            String password = emailAuthenticationMap.get(email);

            if (password.equals(req.getAuthentication())) {
                emailAuthenticationMap.remove(email);
                return new AuthenticationResp(true);
            } else {
                return new AuthenticationResp(false);
            }
        }catch (Exception e) {
            throw new ErrorResp("요청이 정상적으로 처리되지 않았습니다.");
        }
    }

    @Override
    public String makeSecretNumber() {
        Random random = new Random();
        int password = 100000 +random.nextInt(900000);
        return String.valueOf(password);
    }
}
