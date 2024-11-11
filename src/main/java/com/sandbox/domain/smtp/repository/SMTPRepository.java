package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailAuthentication;
import com.sandbox.domain.smtp.exception.ErrorResp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class SMTPRepository {
    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void deleteAll() {
        em.createQuery("delete from EmailAuthentication").executeUpdate();
    }

    public void createEmailAuthentication(EmailAuthentication ea){
        try {
            em.persist(ea);
        } catch (Exception e) {
            throw new ErrorResp("인증 생성 불가");
        }
    }

    public EmailAuthentication getEmailAuthentication(String email){
        return em.find(EmailAuthentication.class, email);
    }

    public void deleteEmailAuthentication(String email){
        em.remove(getEmailAuthentication(email));
    }
}
