package com.ljproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.User;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	 void deleteUserById(long id);
	 User findByEmail(String email);
	

	
}
