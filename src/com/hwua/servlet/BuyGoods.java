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
import com.hwua.entity.User;
import com.hwua.service.IGoodsService;
import com.hwua.service.IOrderService;
import com.hwua.service.impl.GoodsServiceImpl;
import com.hwua.service.impl.OrderServiceImpl;

/**
 * 购物车结算并产生订单的servlet
 */
@WebServlet("/doBuy")
public class BuyGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IOrderService ios = new OrderServiceImpl();
		IGoodsService igd = new GoodsServiceImpl();
		Order o = null;
		int result = 0;
		int result2 = 0;
		//用来表示商品总价
		double sumPrice = 0;
		//从网页上得到商品id集合
		String[] spid = request.getParameterValues("pId");
		String[] sprice = request.getParameterValues("price");
		String[] scount = request.getParameterValues("number");
		String[] sptock = request.getParameterValues("hpStock");
		if(spid!=null && spid.length!=0 && sprice!=null && sprice.length!=0
				&& scount!=null && scount.length!=0 && sptock!=null && sptock.length!=0){
		int[] pid = new int[spid.length];
		//从网页上得到商品价格集合		
		double[] price = new double[sprice.length];
		//从网页上得到商品数量集合		
		int[] count = new int[scount.length];
		//从网页上得到商品库存集合		
		int[] stock = new int[sptock.length];		
		//通过循环给商品id赋值
		for (int i = 0; i < pid.length; i++) {
			if(spid[i]!=null && !"".equals(spid[i])){
				pid[i]=Integer.parseInt(spid[i]);
			}			
		}		
		//通过循环给商品价格和数量赋值
		for (int i = 0; i < pid.length; i++) {
			if(sprice[i]!=null && !"".equals(sprice[i])){
			price[i]=Integer.parseInt(sprice[i]);
			}
		}
		for (int i = 0; i < scount.length; i++) {
			if(scount[i]!=null && !"".equals(scount[i])){
			count[i]=Integer.parseInt(scount[i]);
			}
		}
		//通过循环给商品总价赋值
		for (int i = 0; i < price.length; i++) {
			sumPrice+= price[i]*count[i];
		}
		//System.out.println(sumPrice);
		//通过循环得到商品的库存赋值
		for (int i = 0; i < sptock.length; i++) {
			if(sptock[i]!=null && !"".equals(sptock[i])){
			stock[i]=Integer.parseInt(sptock[i]);
			}
		}
		Object obj = request.getSession().getAttribute("user");
		User user = (User)obj;
		//从session域中得到用户的id，name和address
		int uid  = user.getHu_user_id();
		String uName = user.getHu_user_name();
		String uAddress = user.getHu_address();
		//给订单中的字段赋值,产生一个订单
		o = new Order(uid,uName,uAddress,sumPrice);
		//将用户id，name，address和商品总价传入业务层
		result = ios.insertOrder(o,pid,count);
		System.out.println(pid.length);
		System.out.println(result);
		if (result==1+ pid.length*2 && o!=null) {			
			System.out.println("显示订单成功");
			//将用户id传入业务层，显示订单
			Order order = ios.showOrder(uid);
			int orderId = order.getHo_id();
			System.out.println(orderId);
			//将订单id放入req域中
			request.setAttribute("ho_id", orderId);
			//将订单id，商品id集合，商品数量集合以及商品单价集合传入业务层
			result2 =ios.insertOrderDetail(order.getHo_id(),pid,count,price);
			//response.getWriter().write("1");
			//点击订单时，跳转界面到orders_view.jsp中
			request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("doShowCart").forward(request, response);
		}
		}else{
			//response.getWriter().write("0");
			//System.out.println("参数验证错误");
			//request.getRequestDispatcher("doCartNoEmpty").forward(request, response);
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
