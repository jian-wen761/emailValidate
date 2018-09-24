package com.email.servlet;

import com.email.service.UserService;
import com.email.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		System.out.println(userName+" "+password+" "+email);
		UserService userService=new UserServiceImpl();
		
		if(userService.doRegister(userName,password,email)){
			request.setAttribute("msg", "注册成功，请登录邮箱激活账号");
		}else{
			request.setAttribute("msg", "注册失败，请检查相关信息");
		}
		
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
	
	
}
