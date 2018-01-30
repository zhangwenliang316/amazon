package com.hwua.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.Category;

public interface ICategoryDao {
	/**
	 * 获得左侧商品列表的父元素
	 * @return 成功列表集合 失败null
	 * @throws SQLException
	 */
	public List<Category> QueryCategoryParent() throws SQLException;
	/**
	 * 获得左侧商品列表子元素
	 * @param catePra 父类列表对象
	 * @return 成功列表集合  失败null
	 */
	public List<Category> QueryCategoryChild(Category catePra) throws SQLException;
	/**
	 * 通过商品的父类id得到父类商品对象
	 * @param 商品的父类id
	 * @return 成功返回父类商品对象  失败null
	 * @throws SQLException 
	 */
	public Category QueryParentCategory(int parentId) throws SQLException;
	/**
	 * 通过商品的子类id得到父类商品对象
	 * @param 商品的子类id
	 * @return 成功返回子类商品对象  失败null
	 * @throws SQLException 
	 */
	public Category QueryChildCategory(int childId)throws SQLException;
}
