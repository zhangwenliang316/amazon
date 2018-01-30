package com.hwua.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hwua.dao.IOrderDao;
import com.hwua.entity.Order;
import com.hwua.entity.OrderDetail;
import com.hwua.tools.JDBCUtils;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public int insertOrder(Order o) throws SQLException {
		QueryRunner run = new QueryRunner();
		//QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO HWUA_ORDER VALUES(SEQ_ORDER.NEXTVAL,?,?,?,SYSDATE,?,1,1)";
		int result = run.update(JDBCUtils.getConnection(),sql,o.getHo_user_id(),o.getHo_user_name(),o.getHo_user_address()
				,o.getHo_cost());
		return result;
	}

	@Override
	public Order showOrder(int uid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * from HWUA_ORDER WHERE HO_USER_ID = ? ORDER BY HO_CREATE_TIME DESC";
		Order o = run.query(sql, new BeanHandler<>(Order.class),uid);
		return o;
	}

	@Override
	public int insertOrderDetail(int ho_id, int pid, int count, double price) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO HWUA_ORDER_DETAIL VALUES(SEQ_DETAIL.NEXTVAL,?,?,?,?)";
		int result = run.update(sql,ho_id,pid,count,price);
		return result;
	}

	@Override
	public List<OrderDetail> showOrderDetail(int ho_id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * from HWUA_ORDER_DETAIL WHERE HO_ID = ?";
		List<OrderDetail> list = run.query(sql, new BeanListHandler<>(OrderDetail.class),ho_id);
		return list;
	}

	@Override
	public Order showOrderById(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * from HWUA_ORDER WHERE HO_ID = ?";
		Order o = run.query(sql, new BeanHandler<>(Order.class),id);
		return o;
	}

}
