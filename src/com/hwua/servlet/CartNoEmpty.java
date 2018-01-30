package com.hwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Cart;
import com.hwua.entity.User;
import com.hwua.service.ICartService;
import com.hwua.service.impl.CartServiceImpl;

/**
 * 验证购物车是否为空的servlet
 */
@WebServlet("/doCartNoEmpty")
public class CartNoEmpty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICartService ics = new CartServiceImpl();
		Object obj = request.getSession().getAttribute("user");
		User user = (User)obj;
		//从session域中得到用户的id
		int uid  = user.getHu_user_id();
		List<Cart> list = ics.getAll(uid);
		if (list.size()==0) {
			response.getWriter().write("0");
		}else{
			response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
