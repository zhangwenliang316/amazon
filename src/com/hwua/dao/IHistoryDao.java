package com.hwua.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.History;

public interface IHistoryDao {
	/**
	 * 添加到数据库用户足迹的
	 * @param pid 商品id
	 * @param uid 用户id
	 * @return 成功返回1  0失败
	 */
	public int insertHistory(int pid, int uid) throws SQLException;
	/**
	 * 查询用户的历史记录
	 * @param uid 用户id 
	 * @return 成功返回历史集合 失败null
	 */
	public List<History> queryHistory(int uid) throws SQLException;

}
