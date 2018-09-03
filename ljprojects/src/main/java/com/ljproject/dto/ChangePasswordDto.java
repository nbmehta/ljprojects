package com.ljproject.dto;

public class ChangePasswordDto {
	
	private String password;
	
	
	private String OldPassword;
	
	private String confirmpasword;
	
	
	private String token;
	
	private long userId;
	
	public String getOldPassword() {
		return OldPassword;
	}

	public void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpasword() {
		return confirmpasword;
	}

	public void setConfirmpasword(String confirmpasword) {
		this.confirmpasword = confirmpasword;
	}

}
