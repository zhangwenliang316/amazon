package com.hwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Goods;
import com.hwua.entity.Pager;
import com.hwua.service.IGoodsService;
import com.hwua.service.impl.GoodsServiceImpl;
/**
 * 点击左侧商品栏分页展示商品的servlet
 */
@WebServlet("/doPage")
public class GoodsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cate = request.getParameter("cate");
		
		if("child".equals(cate)){
			//获得商品小类
			String hpcid = request.getParameter("hpcId");
				
			//获得当前下标页
			int pageNum = 1;
			String page = request.getParameter("page");
			if(page!=null && !"".equals(page)){
				pageNum = Integer.parseInt(page);
			}
				Pager pager = new Pager(pageNum);
					
			//传入业务层进行数据数据判断查询
			IGoodsService igs = new GoodsServiceImpl();
			List<Goods> list = igs.getGoods(pager,hpcid);
					
			//将pager和list存入域中
			request.setAttribute("pList", list);
			request.setAttribute("pager", pager);
					
			request.getRequestDispatcher("doNewsQuery").forward(request, response);
		}else if("parent".equals(cate)){
			//获得商品大类
			String hpcid = request.getParameter("hpcId");
			
			//获得当前下标页
			int pageNum = 1;
			String page = request.getParameter("page");
			if(page!=null && !"".equals(page)){
				pageNum = Integer.parseInt(page);
			}
				Pager pager = new Pager(pageNum);
					
			//传入业务层进行数据数据判断查询
			IGoodsService igs = new GoodsServiceImpl();
			List<Goods> list = igs.getGoodsParent(pager,hpcid);
					
			//将pager和list存入域中
			request.setAttribute("pList", list);
			request.setAttribute("pager", pager);
					
			request.getRequestDispatcher("doNewsQuery").forward(request, response);
		}else if("".equals(cate)){
			//获得当前下标页
			int pageNum = 1;
			String page = request.getParameter("page");
			if(page!=null && !"".equals(page)){
				pageNum = Integer.parseInt(page);
			}
			Pager pager = new Pager(pageNum);
			
			//传入业务层进行数据数据判断查询
			IGoodsService igs = new GoodsServiceImpl();
			List<Goods> list = igs.getGoods(pager);
			
			//将pager和list存入域中
			request.setAttribute("pList", list);
			request.setAttribute("pager", pager);
			//请求转发
			request.getRequestDispatcher("doNewsQuery").forward(request, response);
		}		
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
