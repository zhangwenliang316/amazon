package com.hwua.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hwua.dao.ICartDao;
import com.hwua.dao.IGoodsDao;
import com.hwua.dao.IOrderDao;
import com.hwua.dao.impl.CartDaoImpl;
import com.hwua.dao.impl.GoodsDaoImpl;
import com.hwua.dao.impl.OrderDaoImpl;
import com.hwua.entity.Goods;
import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;
import com.hwua.service.IOrderService;
import com.hwua.tools.JDBCUtils;

public class OrderServiceImpl implements IOrderService{

	@Override
	public int insertOrder(Order o,int[]pid,int[]count) {
		IOrderDao iod = new OrderDaoImpl();
		IGoodsDao igd = new GoodsDaoImpl();
		ICartDao icd = new CartDaoImpl();
		int result = 0;
		//int result2 = 0;
		//int result3 = 0;
		try {
			//开启事物
			JDBCUtils.beginTransaction();
			//生成订单
			result += iod.insertOrder(o);
			//更改商品库存
			for (int i = 0; i < pid.length; i++) {
				result += igd.updateStockById(pid[i],count[i]);
			}
			//删除购物车			
			result += icd.deleteCartByUserId(o.getHo_user_id());
			//提交事务
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {				
				//执行失败后回滚
				e.printStackTrace();
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		
		}								
		return result;
	}

	@Override
	public Order showOrder(int uid) {
		IOrderDao iod = new OrderDaoImpl();
		Order o = new Order();
		try {
			o=iod.showOrder(uid);
			if (o!=null) {
				System.out.println(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public int insertOrderDetail(int ho_id, int[] pid, int[] count, double[] price) {
		IOrderDao iod = new OrderDaoImpl();
		int result = 0;
		for (int i = 0; i < pid.length; i++) {
			try {
				result = iod.insertOrderDetail(ho_id,pid[i],count[i],price[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return result;
	}

	@Override
	public List<OrderDetail> showOrderDetail(int ho_id) {
		IOrderDao iod = new OrderDaoImpl();
		IGoodsDao igd = new GoodsDaoImpl();
		List<OrderDetail> list = new ArrayList<>();
		try {
			list = iod.showOrderDetail(ho_id);
			for (OrderDetail or : list) {
				Goods product =igd.getOneGoods(or.getHp_id());
				or.setProduct(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Order showOrderById(int id) {
		IOrderDao iod = new OrderDaoImpl();
		Order o = new Order();
		try {
			o=iod.showOrderById(id);
			if (o!=null) {
				System.out.println(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

}
