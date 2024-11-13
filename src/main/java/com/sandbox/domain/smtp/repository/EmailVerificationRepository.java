package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailVerification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepository {

    @PersistenceContext
    private final EntityManager em;

    public Optional<EmailVerification> findByEmail(String email) {
        return em.createQuery("SELECT ev FROM EmailVerification ev WHERE ev.email = :email", EmailVerification.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<EmailVerification> findByEmailAndCode(String email, String code) {
        return em.createQuery("SELECT ev FROM EmailVerification ev WHERE ev.email = :email AND ev.code = :code AND ev.verified = false", EmailVerification.class)
                .setParameter("email", email)
                .setParameter("code", code)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void save(EmailVerification emailVerification) {
        if (emailVerification.getId() == null) {
            em.persist(emailVerification);
        } else {
            em.merge(emailVerification);
        }
    }

    public void delete(EmailVerification emailVerification) {
        em.remove(em.contains(emailVerification) ? emailVerification : em.merge(emailVerification));
    }
}
