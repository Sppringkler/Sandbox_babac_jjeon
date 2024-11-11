package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailVerification;
import com.sandbox.domain.smtp.repository.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final EmailVerificationRepository emailVerificationRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailVerificationRepository emailVerificationRepository) {
        this.mailSender = mailSender;
        this.emailVerificationRepository = emailVerificationRepository;
    }

    @Override
    public EmailResp sendEmail(EmailReq emailReq) {
        if (!emailReq.isValidEmail()) {
            throw new IllegalArgumentException("유효한 이메일 형식이어야 합니다.");
        }

        String code = generateAuthenticationCode();
        try {
            sendEmail(emailReq.getEmail(), code);

            Optional<EmailVerification> existingVerification = emailVerificationRepository.findByEmail(emailReq.getEmail());
            EmailVerification emailVerification = existingVerification.orElse(new EmailVerification());
            emailVerification.setEmail(emailReq.getEmail());
            emailVerification.setCode(code);
            emailVerification.setVerified(false);
            emailVerificationRepository.save(emailVerification);

            return new EmailResp(true);
        } catch (MessagingException e) {
            return new EmailResp(false);
        }
    }

    @Override
    public EmailResp verifyAuthenticationCode(String email, String code) {
        Optional<EmailVerification> verification = emailVerificationRepository.findByEmailAndCode(email, code);

        if (verification.isPresent()) {
            emailVerificationRepository.delete(verification.get());
            return new EmailResp(true);
        }
        return new EmailResp(false);
    }

    private void sendEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Your Verification Code");
        helper.setText("Your verification code is: " + code);

        mailSender.send(message);
    }

    private String generateAuthenticationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 6자리 숫자 코드 생성
    }
}
