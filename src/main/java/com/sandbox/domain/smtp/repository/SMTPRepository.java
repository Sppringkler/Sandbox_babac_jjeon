package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailAuthentication;
import com.sandbox.domain.smtp.exception.ErrorResp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SMTPRepository {
    @PersistenceContext
    private EntityManager em;

    public boolean createEmailAuthentication(EmailAuthentication ea){
        try {
            em.persist(ea);
        } catch (Exception e) {
            throw new ErrorResp("인증 생성 불가");
        }
        return true;
    }

    public EmailAuthentication getEmailAuthentication(String email){
        return em.find(EmailAuthentication.class, email);
    }

    public void deleteEmailAuthentication(String email){
        em.remove(getEmailAuthentication(email));
    }
}
