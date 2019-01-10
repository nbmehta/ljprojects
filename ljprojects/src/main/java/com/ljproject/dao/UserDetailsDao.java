package com.ljproject.dao;



public interface UserDetailsDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	void deleteUserById(Long id);

	

	void deleteById(int id);
}
