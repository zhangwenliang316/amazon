package com.hwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.News;
import com.hwua.service.INewsService;
import com.hwua.service.impl.NewsServiceImpl;
/**
 * 用户点击新闻，展示单条新闻的servlet
 */
@WebServlet("/doOneNews")
public class OneNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");
		//根据id查询单个新闻对象
		
		INewsService ins = new NewsServiceImpl();
		News news = ins.getOneNews(nid);
		//查找网页右侧新闻栏的单条新闻并存入req域中，在点击该新闻时显示在网页上
		if(news!=null){
			request.setAttribute("newsInfo", news);
			request.getRequestDispatcher("doNews").forward(request, response);
		}else{
			response.getWriter().write("没有该条新闻");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
