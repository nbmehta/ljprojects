package com.ljproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.User;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	
    Optional<User> findByUsernameOrEmail(String username, String email);
    List<User> findByIdIn(List<Long> userIds);
    Optional<User> findByUsername(String username);
    void deleteUserById(long id);
	User findByEmail(String email);
	Optional<User> findById(Long id);
	
}
