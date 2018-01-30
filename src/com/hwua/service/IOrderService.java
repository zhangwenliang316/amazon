package com.hwua.service;

import java.util.List;

import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;

public interface IOrderService {
	/**
	 * 购买成功添加一条订单记录的业务逻辑
	 * @param order，商品id集合，商品数量集合
	 * @return 成功1  失败0
	 */
	public int insertOrder(Order o, int[] pid, int[] count);
	/**
	 * 购买成功后显示订单记录的业务逻辑
	 * @param 用户id
	 * @return 成功返回order对象  失败返回null
	 */
	public Order showOrder(int uid);
	/**
	 * 产生订单后添加订单中商品详情记录的业务逻辑
	 * @param 订单id，商品id集合，商品数量集合，商品价格集合
	 * @return 成功1  失败0
	 */
	public int insertOrderDetail(int ho_id, int[] pid, int[] count, double[] price);
	/**
	 * 显示订单详情的业务逻辑
	 * @param 订单id
	 * @return 成功返回订单详情集合  失败返回null
	 */
	public List<OrderDetail> showOrderDetail(int ho_id);
	/**
	 * 通过订单号查询订单的业务逻辑
	 * @param 订单id
	 * @return 成功返回订单对象  失败返回null
	 */
	public Order showOrderById(int id);
}
