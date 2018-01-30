package com.hwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Goods;
import com.hwua.entity.History;
import com.hwua.entity.User;
import com.hwua.service.IGoodsService;
import com.hwua.service.impl.GoodsServiceImpl;
/**
 * 用户登陆后，展示用户浏览足迹的servlet
 */
@WebServlet("/doShowHistory")
public class ShowHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj = request.getSession().getAttribute("user");
		IGoodsService igs = new GoodsServiceImpl();
		//通过登录的用户得到该用户的浏览记录
		//System.out.println("111");
		if(obj !=null ) {
			User user = (User)obj;
			int uid  = user.getHu_user_id();
			List<History> list = igs.showHistory(uid);
		/*	for (History history : list) {
				System.out.println(history);*/
			/*}*/
			//显示用户的浏览记录并将记录存入req域中
			request.setAttribute("history", list);
		}
		//未登录的用户不显示浏览记录直接跳转到主页显示
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
