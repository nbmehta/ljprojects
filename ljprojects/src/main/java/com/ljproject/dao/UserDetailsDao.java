package com.ljproject.dao;

import com.ljproject.model.UserAttempts;

public interface UserDetailsDao {
	
	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	UserAttempts getUserAttempts(String username);

}
