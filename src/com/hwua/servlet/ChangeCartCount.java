package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.service.ICartService;
import com.hwua.service.impl.CartServiceImpl;

/**
 * 通过商品id修改购物车中商品数量的servlet
 */
@WebServlet("/doChangeCartCount")
public class ChangeCartCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICartService ics = new CartServiceImpl();
		String spid = request.getParameter("pid");
		String scount = request.getParameter("count");
		int pid = 1;
		int count = 1;
		if(spid!=null && !"".equals(spid)){
			pid = Integer.parseInt(spid);
		}
		if(scount!=null && !"".equals(scount)){
			count = Integer.parseInt(scount);
		}
		int result = ics.changeCartCount(pid,count);
		
		if(result == 1 ){
			System.out.println("修改数量成功");
			response.getWriter().write("1");
		}else{
			response.getWriter().write("0");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
