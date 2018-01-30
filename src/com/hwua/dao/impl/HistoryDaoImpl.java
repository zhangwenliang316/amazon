package com.hwua.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hwua.dao.IHistoryDao;
import com.hwua.entity.History;
import com.hwua.tools.JDBCUtils;

public class HistoryDaoImpl implements IHistoryDao {

	@Override
	public int insertHistory(int pid, int uid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql= "INSERT INTO HWUA_HISTORY VALUES(SEQ_HWUA_HISTORY.NEXTVAL,?,?,SYSDATE)";
		int result =run.update(sql,pid,uid);
		return result;
	}

	@Override
	public List<History> queryHistory(int uid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		//将用户点击的商品的记录按照时间倒序排列显示在网页上
		String sql = "SELECT * FROM HWUA_HISTORY WHERE USERID = ? ORDER BY QUERYDATE DESC";
		List<History> list = run.query(sql, new BeanListHandler<>(History.class),uid);
		return list;
	}

}
