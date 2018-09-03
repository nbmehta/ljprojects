package com.ljproject.util;

import com.ljproject.model.User;

public interface TokenService {
	public String sendRestLink(User user,String token);
	public String validatePasswordResetToken(long id, String token);
}
