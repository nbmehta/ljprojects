package com.ljproject.dao;

import java.util.List;

import com.ljproject.model.UserProfile;

public interface UserDetailsDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	void deleteUserById(Long id);

	List<UserProfile> findAll();

	UserProfile findById(int id);

	void save(UserProfile userProfile);

	List<UserProfile> findAllByUserId(int userId);

	void deleteById(int id);
}
