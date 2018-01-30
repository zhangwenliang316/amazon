package com.hwua.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.Category;
import com.hwua.service.ICategoryService;
import com.hwua.service.impl.CategoryServiceImpl;
/**
 * 展示网页左侧商品栏的servlet
 */
@WebServlet("/doQuery")
public class GoodsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 左侧商品分类并关联到商品展示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICategoryService ics = new CategoryServiceImpl();
		
		Map<Category,List<Category>> cMap = new LinkedHashMap<Category,List<Category>>();
		//获得商品大类的列表集合
		List<Category> parentList = ics.getParentCate();
		
		for (Category category : parentList) {
			//获得商品小类的列表集合,利用获得的商品大类的对象的id获得商品小类的列表
			List<Category> childList = ics.getChildCate(category);
			//循环放入map中
			cMap.put(category, childList);
		}
		
		if(cMap != null){
			//将网页左侧商品栏存入session域中，在加载页面时显示在网页上，并跳转到网页中间显示所有商品的区域
			request.getSession().setAttribute("cMap", cMap);
			request.getRequestDispatcher("doAll").forward(request, response);
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
