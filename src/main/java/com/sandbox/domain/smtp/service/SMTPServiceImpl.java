package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailVerification;
import com.sandbox.domain.smtp.exception.ErrorResp;
import com.sandbox.domain.smtp.repository.EmailVerificationRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMTPServiceImpl implements SMTPService {

    private final JavaMailSender mailSender;
    private final EmailVerificationRepository repo;

    @Override
    @Transactional
    public EmailResp sendSecretNumber(EmailReq req) {
        String code = generateAuthenticationCode();

        try {
            // 이메일 전송
            sendEmail(req.getEmail(), code);

            // 기존 엔티티 확인 및 삭제
            Optional<EmailVerification> existingVerification = repo.findByEmail(req.getEmail());
            existingVerification.ifPresent(repo::delete);

            // 새로운 엔티티 생성 후 저장
            EmailVerification emailVerification = new EmailVerification(req.getEmail(), code);
            repo.save(emailVerification);

            // 명세서에 따른 응답 형식으로 반환
            return new EmailResp(true);

        } catch (MessagingException e) {
            return new EmailResp(false); // 실패 시 false 응답
        }
    }



    @Override
    @Transactional
    public AuthenticationResp authenticate(AuthenticationReq req) {
        // 이메일과 인증번호로 조회
        Optional<EmailVerification> emailVerification = repo.findByEmailAndCode(req.getEmail(), req.getAuthentication());

        // 인증번호가 맞으면 true, 아니면 false 반환
        if (emailVerification.isPresent()) {
            repo.delete(emailVerification.get());
            return new AuthenticationResp(true);
        } else {
            return new AuthenticationResp(false);
        }
    }

    private void sendEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("SANDBOX _ SMTP 인증 메일");
        helper.setText("인증코드 : " + code);

        mailSender.send(message);
    }

    private String generateAuthenticationCode() {
        return String.format("%06d", new Random().nextInt(1000000)); // 6자리 랜덤 숫자 만듦
    }
}
