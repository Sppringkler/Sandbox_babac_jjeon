package com.sandbox.domain.smtp.repository;

import com.sandbox.domain.smtp.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    Optional<EmailVerification> findByEmail(String email);

    @Query("SELECT ev FROM EmailVerification ev WHERE ev.email = :email AND ev.code = :code AND ev.verified = false")
    Optional<EmailVerification> findByEmailAndCode(@Param("email") String email, @Param("code") String code);
}
