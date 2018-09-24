package com.email.service;


public interface UserService {

	boolean doRegister(String userName, String password, String email);

	boolean activeUser(String code);
	
}
