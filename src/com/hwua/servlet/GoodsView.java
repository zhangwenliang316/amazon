package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Goods;
import com.hwua.entity.User;
import com.hwua.service.IGoodsService;
import com.hwua.service.impl.GoodsServiceImpl;
/**
 * 用户登陆后，点击商品，添加浏览记录到用户浏览足迹的servlet
 */
@WebServlet("/doView")
public class GoodsView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pId");
		//从网页上得到商品id并传入的逻辑层
		//处理足迹的操作:传入商品id,用户id,当前时间
		IGoodsService igs = new GoodsServiceImpl();
		Goods goods = igs.getOne(pid);
		Object obj = request.getSession().getAttribute("user");
		if(obj !=null ) {
			//用户登录时用户点击商品则将该商品加入浏览足迹中
			User user = (User)obj;
			int uid  = user.getHu_user_id();
			int result = igs.addHistory(pid,uid);			
		}
		if(goods !=null){
			//用户登录时显示用户浏览足迹
			request.setAttribute("pro", goods);
			request.getRequestDispatcher("product_view.jsp").forward(request, response);
		}else{
			System.out.println("找不到该商品");
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
