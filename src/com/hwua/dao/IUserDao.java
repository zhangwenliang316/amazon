package com.hwua.dao;

import java.sql.SQLException;

import com.hwua.entity.User;

public interface IUserDao {
	/**
	 * 根据用户名查询数据库是否重复
	 * @param hu_USER_NAME 用户名
	 * @return 成功返回用户对象 失败返回null
	 */
	public User checkByName(String hu_USER_NAME) throws SQLException;
	/**
	 * 添加用户信息的数据库
	 * @param u  用户对象
	 * @return 成功返回1  0失败
	 */
	public int insertUser(User u) throws SQLException;
	/**
	 * 根据邮箱查询数据库是否重复
	 * @param email 邮箱名称
	 * @return 成功返回对象 失败null
	 */
	public User checkByEmail(String email) throws SQLException;
	/**
	 * 将用户对象输入到数据库进行比对
	 * @param uLogin 用户对象
	 * @return 成功返回用户对象  失败返回null
	 */
	public User login(User uLogin) throws SQLException;

}
