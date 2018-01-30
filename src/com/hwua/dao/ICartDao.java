package com.hwua.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.Cart;
import com.hwua.entity.Goods;

public interface ICartDao {
	/**
	 * 用户根据购物车中的商品id查询商品
	 * @param pid 商品id
	 * @param uid 用户id
	 * @return 成功返回商品对象  失败返回null
	 */
	public Cart checkCartById(int pid, int uid) throws SQLException;
	/**
	 * 将购物车对象添加到数据库
	 * @param cart 购物车对象
	 * @return 成功1  失败0
	 */
	public int insertCart(Cart cart)throws SQLException;
	/**
	 * 对购物车对象进行更新
	 * @param cart
	 * @return 成功返回1  失败0
	 */
	public int updateCart(int pcount,int pid,int uid)throws SQLException;
	/**
	 * 获得当前产品的数量
	 * @param pid
	 * @param uid 
	 * @return
	 */
	public int getCount(int pid, int uid)throws SQLException;
	/**
	 * 根据用户id查出所有的商品
	 * @param uid 用户id
	 * @return 成功返回购物车集合  失败null
	 */
	public List<Cart> getAll(int uid) throws SQLException;
	
	/**
	 * 根据商品id查询商品所有字段的方法
	 * @param pid 商品id
	 * @return 成功返回商品对象  失败null
	 */
	public Goods queryById(int pid) throws SQLException;
	/**
	 * 根据购物车中的商品id删除购物车商品的方法
	 * @param cid 购物车id
	 * @return 成功返回1  0失败
	 */
	public int deleteCartById(int cid) throws SQLException;
	/**
	 * 根据用户id删除购物车的方法
	 * @param uid 用户id
	 * @return 成功返回1  0失败
	 */
	public int deleteCartByUserId(int uid) throws SQLException;
	/**
	 * 根据购物车中的商品id修改购物车中商品的数量方法
	 * @param pid 购物车中商品的id,count 购物车中商品的数量
	 * @return 成功返回1  0失败
	 */
	public int changeCartCount(int pid, int count) throws SQLException;

}
