package com.email.dao;

import com.email.model.User;

public interface UserDao {

	int save(User user);

	int activeUser(String code);

}
