package com.hwua.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.hwua.dao.ICartDao;
import com.hwua.dao.impl.CartDaoImpl;
import com.hwua.entity.Cart;
import com.hwua.entity.Goods;
import com.hwua.service.ICartService;

public class CartServiceImpl implements ICartService {

	@Override
	public int getCount(int pid, int uid) {
		ICartDao icd = new CartDaoImpl();
		int quantity = 0;
		try {
			quantity = icd.getCount(pid, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	@Override
	public int addCart(Cart cart) {
		ICartDao icd = new CartDaoImpl();
		//1.查询数据,如果商品id是重复的,就修改count的数据
		Cart c = null;
		int result1 = 0;
		int result2 = 0;
		try {
			c = icd.checkCartById(cart.getPid(),cart.getUserid());
			//2.如果id不重复,就直接添加到数据库
			if(c == null){
				result1 = icd.insertCart(cart);
			}else{
				//3.获取从数据库中该商品的数量
				int count = icd.getCount(cart.getPid(),cart.getUserid());
				//4.将相加后获得的商品数量和商品id传入数据库
				int pcount = count + cart.getQuantity();
				System.out.println("数据库中查出的商品数量:"+count);
				System.out.println("相加后得到的商品数量为"+pcount);
				result2 = icd.updateCart(pcount,cart.getPid(),cart.getUserid());
				return result2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result1;
		
	}
	@Override
	public List<Cart> getAll(int uid){
		ICartDao icd = new CartDaoImpl();
		List<Cart> list = null;
		//根据用户id查出所有的商品
		try {
			list = icd.getAll(uid);
			//System.out.println(list);			
			if(list.size()!=0){
				System.out.println("得到指定用户的购物车中的商品集合");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	@Override
	public List<Cart> showCart(int uid) {
		ICartDao icd = new CartDaoImpl();
		List<Cart> list = null;
		try {
			//1.根据用户id查出所有的商品
			list = icd.getAll(uid);
			if(list!= null){
				//2.循环填充商品对象
				for (int i = 0; i < list.size(); i++) {
					Goods goods = icd.queryById(list.get(i).getPid());
					list.get(i).setGoods(goods);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteCart(int cid) {
		ICartDao icd = new CartDaoImpl();
		int result = -1;
		try {
			result = icd.deleteCartById(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}

	@Override
	public int changeCartCount(int pid, int count) {
		ICartDao icd = new CartDaoImpl();
		int result = -1;
		try {
			result = icd.changeCartCount(pid,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
