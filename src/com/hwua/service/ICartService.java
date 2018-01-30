package com.hwua.service;

import java.util.List;

import com.hwua.entity.Cart;

public interface ICartService {
	/**
	 * 添加到购物车的逻辑
	 * @param cart 购物车对象
	 * @return
	 */
	int addCart(Cart cart);
	/**
	 * 通过用户id得到购物车中商品集合的逻辑
	 * @param uid 用户id
	 * @return 成功list集合，失败返回null
	 */
	List<Cart> getAll(int uid);
	/**
	 * 查看购物车的逻辑
	 * @param uid 用户id
	 * @return
	 */
	List<Cart> showCart(int uid);
	/**
	 * 根据购物车id删除该购物车的逻辑
	 * @param cid
	 * @return
	 */
	int deleteCart(int cid);
	/**
	 * 根据购物车中的商品id修改该商品的数量的逻辑
	 * @param pid，count
	 * @return
	 */
	int changeCartCount(int pid, int count);
	/**
	 * 根据购物车中的商品id和用户id查询购物车中该商品的数量的逻辑
	 * @param pid，uid
	 * @return
	 */
	int getCount(int pid, int uid);
}
