package com.sandbox.domain.smtp.controller;

import com.sandbox.domain.smtp.dto.AuthenticationReq;
import com.sandbox.domain.smtp.dto.AuthenticationResp;
import com.sandbox.domain.smtp.dto.EmailReq;
import com.sandbox.domain.smtp.dto.EmailResp;
import com.sandbox.domain.smtp.service.SMTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
@RequestMapping("/email")
public class SMTPController {
    private final SMTPService ss;

    @PostMapping
    public ResponseEntity<?> sendSecretNumber(@RequestBody EmailReq req){
        EmailResp resp = ss.sendSecretNumber(req);
        System.out.println("요청이 들어옴!!!!!!!!!");
        System.out.println(resp.isOk());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationReq req){
        AuthenticationResp resp = ss.authenticate(req);
        return ResponseEntity.ok(resp);
    }
}
