package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailVerification;
import com.sandbox.domain.smtp.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMTPServiceImpl implements SMTPService {

    private final JavaMailSender mailSender;
    private final EmailVerificationRepository emailVerificationRepository;

    @Override
    public EmailResp sendSecretNumber(EmailReq req) {
        String code = generateAuthenticationCode();
        try {
            sendEmail(req.getEmail(), code);

            EmailVerification emailVerification = emailVerificationRepository
                    .findByEmail(req.getEmail())
                    .orElse(new EmailVerification());
            emailVerification.setEmail(req.getEmail());
            emailVerification.setCode(code);
            emailVerification.setVerified(false);
            emailVerificationRepository.save(emailVerification);

            return new EmailResp(true);
        } catch (MessagingException e) {
            return new EmailResp(false);
        }
    }

    @Override
    public AuthenticationResp authenticate(AuthenticationReq req) {
        Optional<EmailVerification> verification = emailVerificationRepository
                .findByEmailAndCode(req.getEmail(), req.getAuthentication());

        if (verification.isPresent()) {
            EmailVerification emailVerification = verification.get();
            emailVerification.setVerified(true);
            emailVerificationRepository.save(emailVerification);
            return new AuthenticationResp(true);
        }
        return new AuthenticationResp(false);
    }

    private void sendEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("인증코드입니다");
        helper.setText("인증코드입니다 -> " + code);

        mailSender.send(message);
    }

    private String generateAuthenticationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 6자리 숫자 코드 생성
    }
}
