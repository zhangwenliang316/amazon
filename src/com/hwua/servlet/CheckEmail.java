package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;
/**
 * 检查email的servlet
 */
@WebServlet("/doCheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService ius = new UserServiceImpl();
		String email1 = request.getParameter("email");
		//从网页上得到用户输入的email
		//从数据库中查找是否有用户输入的邮箱，确保用户注册时邮箱唯一
		User email2 = ius.checkEmail(email1);
		if(email2 != null){
			//该邮箱已经注册
			response.getWriter().write("1");
		}else{
			//可以注册
			response.getWriter().write("0");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
