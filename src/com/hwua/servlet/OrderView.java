package com.hwua.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;
import com.hwua.service.IOrderService;
import com.hwua.service.impl.OrderServiceImpl;

/**
 * 订单显示以及订单详情查看servlet
 */
@WebServlet("/doOrderView")
public class OrderView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IOrderService ios = new OrderServiceImpl();
		String str = request.getParameter("ho_id");
		int id =Integer.parseInt(str);
		Order order = ios.showOrderById(id);		
		// 将新产生的订单保存到req域中
		request.setAttribute("order", order);		
		// 将订单id传入业务层，显示订单中商品详情
		List<OrderDetail> olist = new ArrayList<>();
		olist = ios.showOrderDetail(id);
		// 将新产生的订单详情保存到req域中
		request.setAttribute("olist", olist);
		request.getRequestDispatcher("orders_view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
