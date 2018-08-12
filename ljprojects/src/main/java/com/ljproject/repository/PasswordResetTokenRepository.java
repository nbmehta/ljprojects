package com.ljproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.PasswordResetToken;



@Repository("passwordResetTokenRepository")
public interface PasswordResetTokenRepository  extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken(String token);

}
