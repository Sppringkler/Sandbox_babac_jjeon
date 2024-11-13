package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailAuthentication;
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
            sendEmail(req.getEmail(), code);

            //중복되는 값 있나 확인 (set할 경우에는 괜찮은데, 객체 만들어서 넣을거면 삭제 필요)
            Optional<EmailAuthentication> entity = repo.findByEmail(req.getEmail());

            //중복되는거 있으면 삭제하기
            entity.ifPresent(repo::delete);

            EmailAuthentication newEntity = new EmailAuthentication(req.getEmail(), code);
            repo.save(newEntity);

            return new EmailResp(true);
        } catch (MessagingException e) {
            return new EmailResp(false);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResp authenticate(AuthenticationReq req) {
        Optional<EmailAuthentication> verification = repo
                .findByEmailAndCode(req.getEmail(), req.getAuthentication());

        if (verification.isPresent()) {
            EmailAuthentication entity = verification.get();
            repo.delete(entity);
            return new AuthenticationResp(true);
        }

        return new AuthenticationResp(false);
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
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 6자리 숫자 코드 생성
    }
}
