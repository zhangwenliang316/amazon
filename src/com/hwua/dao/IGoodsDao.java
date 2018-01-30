package com.hwua.dao;

import java.sql.SQLException;
import java.util.List;

import com.hwua.entity.Goods;

public interface IGoodsDao {
	/**
	 * 查询所有的商品记录数
	 * @return 所有的商品记录数
	 */
	public int queryCount() throws SQLException;
	/**
	 * 根据条件查询数据库商品
	 * @param start
	 * @param end
	 * @param id 
	 * @return 成功返回商品类型 失败返回null
	 */
	public List<Goods> queryGoods(int start, int end, int id) throws SQLException;
	/**
	 * 从数据库中获得所有商品
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Goods> queryGoods(int start, int end) throws SQLException;
	/**
	 * 从数据库中获得单个商品的
	 * @param id 商品id 
	 * @return 成功返回商品对象  失败null
	 */
	public Goods getOneGoods(int id) throws SQLException;
	/**
	 * 从数据库中获得大列表商品
	 * @param start
	 * @param end
	 * @param id
	 * @return 成功返回商品集合  失败null
	 */
	public List<Goods> queryGoodsParent(int start, int end, int id) throws SQLException;
	/**
	 * 从数据库中获得商品集合,按照库存量从小到大排列
	 * @return 成功返回商品集合  失败null
	 */
	public List<Goods> showGoodsHot() throws SQLException;
	/**
	 * 从数据库中获得模糊查询的结果
	 * @param goodsName
	 * @return 成功返回商品集合 失败null
	 */
	public List<Goods> showQueryGoods(String goodsName) throws SQLException;
	/**
	 * 根据名字进行模糊查询时的总记录数
	 * @param goodsName
	 * @return
	 */
	public int queryCount(String goodsName) throws SQLException;
	/**
	 * 根据商品id修改该商品的库存
	 * @param id stock
	 * @return 成功返回1  失败0
	 */
	public int updateStockById(int id,int stock) throws SQLException;

}
