package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailAuthentication;
import com.sandbox.domain.smtp.exception.ErrorResp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SMTPRepository {
    @PersistenceContext
    private final EntityManager em;

    public void createEmailAuthentication(EmailAuthentication ea){
        try {
            em.persist(ea);
        } catch (Exception e) {
            throw new ErrorResp("인증 생성 불가");
        }
    }

    public Optional<EmailAuthentication> getEmailAuthentication(String email) {
        return em.createQuery("select ea " +
                        "from EmailAuthentication ea " +
                        "where ea.email = :email " +
                        "order by ea.id desc", EmailAuthentication.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findFirst();
    }

    public void deleteEmailAuthentications(String email){
        em.createQuery("delete from EmailAuthentication ea where ea.email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }
}
