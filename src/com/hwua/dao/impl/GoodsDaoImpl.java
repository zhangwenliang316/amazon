package com.hwua.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hwua.dao.IGoodsDao;
import com.hwua.entity.Goods;
import com.hwua.tools.JDBCUtils;

public class GoodsDaoImpl implements IGoodsDao {

	@Override
	public int queryCount() throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM  HWUA_PRODUCT";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();	
		return count;
	}

	@Override
	public List<Goods> queryGoods(int start, int end,int id) throws SQLException{
		//1获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT * FROM (SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_CHILD_ID = ?) T WHERE T.R>? AND T.R<=? ";
		List<Goods> result = run.query(sql, new BeanListHandler<>(Goods.class),id,start,end);		
		return result;
	}

	@Override
	public List<Goods> queryGoods(int start, int end) throws SQLException {
		//1获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT * FROM (SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT) T WHERE T.R>? AND T.R<=? ";
		List<Goods> result = run.query(sql, new BeanListHandler<>(Goods.class),start,end);		
		return result;
	}

	@Override
	public Goods getOneGoods(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT  WHERE HP_ID = ?";
		Goods goods = run.query(sql, new BeanHandler<>(Goods.class),id);
		return goods;
	}

	@Override
	public List<Goods> queryGoodsParent(int start, int end, int id) throws SQLException {
		//1获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT * FROM (SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_ID = ?) T WHERE T.R>? AND T.R<=? ";
		List<Goods> result = run.query(sql, new BeanListHandler<>(Goods.class),id,start,end);		
		return result;
	}

	@Override
	public List<Goods> showGoodsHot() throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT ORDER BY HP_STOCK";
		List<Goods> result = run.query(sql, new BeanListHandler<>(Goods.class));
		return result;
	}

	@Override
	public List<Goods> showQueryGoods(String goodsName) throws SQLException {
		//1获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT * FROM  HWUA_PRODUCT WHERE HP_NAME LIKE ? ";
		List<Goods> result = run.query(sql, new BeanListHandler<>(Goods.class),"%"+goodsName+"%");		
		return result;
	}

	@Override
	public int queryCount(String goodsName) throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM  HWUA_PRODUCT WHERE HP_NAME LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, "%"+goodsName+"%");
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();	
		return count;
		
	}

	@Override
	public int updateStockById(int id,int stock) throws SQLException {
		int result = 0;
		QueryRunner run = new QueryRunner();
		//QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE HWUA_PRODUCT set HP_STOCK = HP_STOCK-? WHERE HP_ID = ?";
		result = run.update(JDBCUtils.getConnection(),sql,stock,id);
		return result;
	}

}
