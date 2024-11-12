package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailAuthentication;
import com.sandbox.domain.smtp.exception.ErrorResp;
import com.sandbox.domain.smtp.repository.SMTPRepository;
import jakarta.annotation.PostConstruct;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SMTPServiceImpl implements SMTPService {
    private final SMTPRepository sr;
    private final JavaMailSender jms;

    @PostConstruct
    public void init() {
        sr.deleteAll();
    }

    @Override
    public EmailResp sendSecretNumber(EmailReq req) {
        try {
            String secretNum = makeSecretNumber();
            EmailAuthentication ea = new EmailAuthentication(req.getEmail(), secretNum);
            sr.createEmailAuthentication(ea);
            
            // 메일 발송 처리
            sendMail(req.getEmail(), secretNum);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorResp("요청이 정상적으로 처리되지 않았습니다.");
        }

        return new EmailResp(true);
    }

    @Override
    public AuthenticationResp authenticate(AuthenticationReq req) {
        try {
            List<EmailAuthentication> emailAuthentications = sr.getEmailAuthentications(req.getEmail());

            for (EmailAuthentication ea : emailAuthentications) {
                if(ea.getAuthentication().equals(req.getAuthentication())) {
                    sr.deleteEmailAuthentications(ea.getEmail());
                    return new AuthenticationResp(true);
                }
            }
            return new AuthenticationResp(false);

        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorResp("요청이 정상적으로 처리되지 않았습니다.");
        }
    }

    @Override
    public String makeSecretNumber() {
        Random random = new Random();
        int password = 100000 +random.nextInt(900000);
        return String.valueOf(password);
    }

    public void sendMail(String email,String secretNum){
        MimeMessage msg = jms.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject("sandbox 이메일 인증 비밀번호 입니다!!");
            helper.setText("인증번호는 "+secretNum +" 입니다. \n" +
                    "인증을 완료해주세요!!", true);
            jms.send(msg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorResp("메세지 작성 실패!");
        }
    }
}
