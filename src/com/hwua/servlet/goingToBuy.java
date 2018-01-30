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
 * 点击直接购买将商品添加到购物车并跳转到购物车界面
 */
@WebServlet("/goingToBuy")
public class goingToBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spid = request.getParameter("pid");
		String scount = request.getParameter("count");
		Object obj = request.getSession().getAttribute("user");
		User user = (User)obj;
		int uid  = user.getHu_user_id();
		int pid = 1;
		int count = 1;
		if(spid!=null && !"".equals(spid)){
			pid = Integer.parseInt(spid);
		}
		if(scount!=null && !"".equals(scount)){
			count = Integer.parseInt(scount);
		}
		System.out.println("商品id:"+ pid);
		System.out.println("商品数量:"+ count);
		System.out.println("用户id:"+uid);
		Cart cart = new Cart(pid,count,uid);
		ICartService ics = new CartServiceImpl();
		//添加商品至购物车
		int result = ics.addCart(cart);
		List<Cart> list = ics.showCart(uid);
		request.setAttribute("cart", list);
		request.getRequestDispatcher("shopping.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
