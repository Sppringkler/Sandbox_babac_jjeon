package com.sandbox.domain.smtp.service;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.entity.EmailAuthentication;
import com.sandbox.domain.smtp.exception.ErrorResp;
import com.sandbox.domain.smtp.repository.SMTPRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMTPServiceImpl implements SMTPService {
    private final SMTPRepository sr;
    private final JavaMailSender jms;

    @Override
    public EmailResp sendSecretNumber(EmailReq req) {
        try {
            String secretNum = makeSecretNumber();
            EmailAuthentication ea = new EmailAuthentication(req.getEmail(), secretNum);
            sr.createEmailAuthentication(ea);
            
            // 메일 발송 처리
            sendMail(req.getEmail(), secretNum);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorResp("요청이 정상적으로 처리되지 않았습니다.");
        }

        return new EmailResp(true);
    }

    @Override
    public AuthenticationResp authenticate(AuthenticationReq req) {
        try {
            if(req.getAuthentication() == sr.getEmailAuthentication(req.getEmail()).getAuthentication()){
                sr.deleteEmailAuthentication(req.getEmail());
                return new AuthenticationResp(true);
            }else{
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

    public void sendMail(String email,String secretNum){
        MimeMessage msg = jms.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject("sandbox 이메일 인증 비밀번호 입니다!!");
            helper.setText("인증번호는 "+secretNum +"입니다. \n" +
                    "인증을 완료해주세요!!", true);
            jms.send(msg);
        } catch (Exception e) {
            throw new ErrorResp("메세지 작성 실패!");
        }
    }
}
