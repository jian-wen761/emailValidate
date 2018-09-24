package com.email.service.impl;

import com.email.dao.UserDao;
import com.email.dao.impl.UserDaoImpl;
import com.email.model.User;
import com.email.service.UserService;
import com.email.util.CodeUtil;
import com.email.util.MailUtil;

public class UserServiceImpl implements UserService {

	@Override
	public boolean doRegister(String userName, String password, String email) {
		// 这里可以验证各字段是否为空
		if(userName==null||password==null||email==null)
			return false;
		//利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
		if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
			return false;
		}
		//生成激活码
		String code=CodeUtil.generateUniqueCode();
		User user=new User(userName,email,password,0,code);
		//将用户保存到数据库
		UserDao userDao=new UserDaoImpl();
		//保存成功则通过线程的方式给用户发送一封邮件
		if(userDao.save(user)>0){
			new Thread(new MailUtil(email, code)).start();;
			return true;
		}
		return false;
	}

	@Override
	public boolean activeUser(String code) {
		UserDao userDao=new UserDaoImpl();
		if(userDao.activeUser(code)>0){
			return true;
		}else{
			return false;
		}
	}
}
