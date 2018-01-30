package com.hwua.servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;
/**
 * 用户注册、登录及退出的servlet
 */
@WebServlet("/doAction")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		
		IUserService ius = new UserServiceImpl();
		if(action==null){
			action="1";
		}
		switch (action) {
		//用户注册的servlet
		case "register":
			//获取表单中携带的数据
			String name = request.getParameter("userName");
			String pwd1 = request.getParameter("passWord");
			String pwd2 = request.getParameter("rePassWord");
			String sex = request.getParameter("sex");
			String data = request.getParameter("birthday");
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date birthday = null; 
			try {
				birthday = sd.parse(data); 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 java.sql.Date birthday2 = new java.sql.Date(birthday.getTime()); 
			
			String identity = request.getParameter("identity");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("address");
			String veryCode = request.getParameter("veryCode");
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("validateCode");
		
			//将数据封装为对象传入逻辑层进行判断	
			User u = new User(name,pwd1,sex,birthday2,identity,email,mobile,address);
			User email2 = ius.checkEmail(email);
			if(pwd1.equals(pwd2) && veryCode.equals(obj) && email2 == null){			
				int result = ius.insertUser(u);
				if(result == 1){
					//说明注册成功
					response.getWriter().write("1");
					System.out.println("注册成功");
				}else {
					//注册失败
					response.getWriter().write("0");
				}			
			}else if(!veryCode.equals(obj)){
				System.out.println("验证码不正确");
			}else if(!pwd1.equals(pwd2)){
				System.out.println("两次输入的密码不一致");
			}
			session.invalidate();
			break;
			
		//用户登录的servlet	
		case "login":
			//获取表单中携带的数据
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String inputCode = request.getParameter("veryCode");
			HttpSession session2 = request.getSession();
			Object loginCode = session2.getAttribute("validateCode");

			//将数据封装为对象,传入逻辑层进行判断
			User uLogin = new User(userName,passWord);
			if(inputCode.equals(loginCode)){
				User loginResult = ius.loginUser(uLogin);
				if(loginResult!=null){
					//登录成功
					System.out.println("登录成功!");
					HttpSession session3 = request.getSession();
					//存入域中用户名和密码
					session3.setAttribute("user", loginResult);
					response.getWriter().write("1");
					//request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					//登录失败
					response.getWriter().write("0");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			}else{
				//-1验证码错误
				response.getWriter().write("-1");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
			break;
		//用户退出的servlet
		case "checkOut":
			HttpSession session3 = request.getSession();
			session3.invalidate();
			System.out.println("退出成功!");
			request.getRequestDispatcher("doQuery").forward(request, response);
			break;

		default:
			break;
		}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
