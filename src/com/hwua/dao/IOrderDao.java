package com.hwua.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;

public interface IOrderDao {
	/**
	 * 购买成功添加一条订单记录的数据库操作
	 * @param 订单
	 * @return 成功返回1，失败返回0
	 */
	public int insertOrder(Order o)throws SQLException;
	/**
	 * 购买成功后显示订单记录的数据库操作
	 * @param 用户id
	 * @return 成功返回order对象，失败返回null
	 */
	public Order showOrder(int uid) throws SQLException;
	/**
	 * 产生订单后添加订单中商品详情记录的数据库操作
	 * @param 订单id，商品id，商品数量，商品价格
	 * @return 成功1  失败0
	 */
	public int insertOrderDetail(int ho_id, int pid, int count, double price)throws SQLException;
	/**
	 * 显示订单详情的数据库操作
	 * @param 订单id
	 * @return 成功返回订单详情集合  失败返回null
	 */
	public List<OrderDetail> showOrderDetail(int ho_id)throws SQLException;
	/**
	 * 通过订单id查询订单的数据库操作
	 * @param 订单id
	 * @return 成功返回订单对象  失败返回null
	 */
	public Order showOrderById(int id)throws SQLException;

}
