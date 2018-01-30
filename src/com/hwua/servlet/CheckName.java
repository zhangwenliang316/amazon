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
 * 检查用户名的servlet
 */
@WebServlet("/doCheckName")
public class CheckName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService ius = new UserServiceImpl();
		String name = request.getParameter("userName");
		//从网页上得到用户输入的用户名
		System.out.println(name);
		//从数据库中查找是否有用户输入的用户名，确保用户注册时用户名唯一	
		User u = ius.checkName(name);
		if(u !=null){
			//如果不为空说明用户名已经注册
			response.getWriter().write("1");
		}else{
			//如果为空说明用户名可以注册
			response.getWriter().write("0");
		}
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
