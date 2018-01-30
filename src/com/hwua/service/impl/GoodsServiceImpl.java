package com.hwua.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.hwua.dao.ICartDao;
import com.hwua.dao.ICategoryDao;
import com.hwua.dao.IGoodsDao;
import com.hwua.dao.IHistoryDao;
import com.hwua.dao.impl.CartDaoImpl;
import com.hwua.dao.impl.CategoryDaoImpl;
import com.hwua.dao.impl.GoodsDaoImpl;
import com.hwua.dao.impl.HistoryDaoImpl;
import com.hwua.entity.Category;
import com.hwua.entity.Goods;
import com.hwua.entity.History;
import com.hwua.entity.Pager;
import com.hwua.service.IGoodsService;

public class GoodsServiceImpl implements IGoodsService {

	@Override
	public List<Goods> getGoods(Pager pager,String sid) {
		IGoodsDao igd = new GoodsDaoImpl();
		List<Goods> result = null;
		int id = 15;
		if(sid!=null && !"".equals(sid)){
			id = Integer.parseInt(sid);
		}
		try {
			//获得总的记录数
			int count = igd.queryCount();
			//将记录总数设置到pager对象中
			pager.setRecordCount(count);
			int start = (pager.getCurrentPage()-1)*pager.PAGE_RECORD;
			int end = start+pager.PAGE_RECORD;
			//调用dao层,查询区间集合
			result = igd.queryGoods(start,end,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Goods> getGoods(Pager pager) {
		IGoodsDao igd = new GoodsDaoImpl();
		List<Goods> result = null;
		
		try {
			//获得总的记录数
			int count = igd.queryCount();
			//将记录总数设置到pager对象中
			pager.setRecordCount(count);
			int start = (pager.getCurrentPage()-1)*pager.PAGE_RECORD;
			int end = start+pager.PAGE_RECORD;
			//调用dao层,查询区间集合
			result = igd.queryGoods(start,end);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<Goods> getGoodsParent(Pager pager, String hpcid) {
		IGoodsDao igd = new GoodsDaoImpl();
		List<Goods> result = null;
		int id = 2;
		if(hpcid!=null && !"".equals(hpcid)){
			id = Integer.parseInt(hpcid);
		}
		try {
			//获得总的记录数
			int count = igd.queryCount();
			//将记录总数设置到pager对象中
			pager.setRecordCount(count);
			int start = (pager.getCurrentPage()-1)*pager.PAGE_RECORD;
			int end = start+pager.PAGE_RECORD;
			//调用dao层,查询区间集合
			result = igd.queryGoodsParent(start,end,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Goods getOne(String pid) {
		IGoodsDao igd = new GoodsDaoImpl();
		ICategoryDao icd = new CategoryDaoImpl();
		int id = 1;
		if(pid!=null && !"".equals(pid)){
			id = Integer.parseInt(pid);
		}
		Goods goods = null;
		try {
			goods = igd.getOneGoods(id);
			int parentId = goods.getHpc_id();
			Category cate1 = icd.QueryParentCategory(parentId);
			goods.setParent_category(cate1);
			int childId = goods.getHpc_child_id();
			Category cate2 = icd.QueryChildCategory(childId);
			goods.setChild_category(cate2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return goods;
	}

	@Override
	public List<Goods> getGoodsHot() {
		IGoodsDao igd = new GoodsDaoImpl();
		List<Goods> list = null;
		try {
			list = igd.showGoodsHot();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Goods> getQueryGoods(String goodsName) {
		IGoodsDao igd = new GoodsDaoImpl();
		List<Goods> list =null;
		try {
			list = igd.showQueryGoods(goodsName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int addHistory(String spid, int uid) {
		IHistoryDao ihd = new HistoryDaoImpl();
		if(uid==0){
			System.out.println("用户不存在!");
		}
		int pid=1;
		if(spid!=null&&!"".equals(spid)){
			pid = Integer.parseInt(spid);
		}
			int result = -1;
			try {
				result = ihd.insertHistory(pid,uid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return result;
	}

	@Override
	public List<History> showHistory(int uid) {
		IHistoryDao ihd = new HistoryDaoImpl();
		ICartDao icd = new CartDaoImpl();
		List<History> list = null;
		try {
			list = ihd.queryHistory(uid);
			if(list != null){
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
	public int updateStockById(int id,int stock){
		IGoodsDao igd = new GoodsDaoImpl();
		int result =0;
		try {
			result = igd.updateStockById(id,stock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


}
