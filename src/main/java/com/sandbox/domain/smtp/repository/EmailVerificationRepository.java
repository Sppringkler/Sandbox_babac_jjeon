package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailAuthentication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepository {

    @PersistenceContext
    private final EntityManager em;

    public Optional<EmailAuthentication> findByEmail(String email) {
        return em.createQuery("SELECT ev FROM EmailAuthentication ev WHERE ev.email = :email", EmailAuthentication.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<EmailAuthentication> findByEmailAndCode(String email, String code) {
        return em.createQuery("SELECT ev FROM EmailAuthentication ev WHERE ev.email = :email AND ev.code = :code AND ev.verified = false", EmailAuthentication.class)
                .setParameter("email", email)
                .setParameter("code", code)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void save(EmailAuthentication emailAuthentication) {
        if (emailAuthentication.getId() == null) {
            em.persist(emailAuthentication);
        } else {
            em.merge(emailAuthentication);
        }
    }

    public void delete(EmailAuthentication emailAuthentication) {
        em.remove(em.contains(emailAuthentication) ? emailAuthentication : em.merge(emailAuthentication));
    }
}
