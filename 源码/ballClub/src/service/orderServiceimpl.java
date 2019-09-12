package service;

import java.sql.SQLException;
import java.util.List;

import dao.orderDaoimpl;
import dao.orderDaointer;
import domain.order;

public class orderServiceimpl implements orderServiceinter{

	orderDaointer orderDao=new orderDaoimpl();
	
	
	
	//存储myorder
	public void addOrder(order order) throws SQLException {
		
		orderDao.addOrder(order);
	}



	//购买order成功功能
	public void buyOrdersuccess(String address,String oid) throws SQLException {
		
		orderDao.buyOrdersuccess(address,oid);
	}



	//根据oid查询order
	public order getOrder(String oid) throws SQLException {
		
		return orderDao.getOrder(oid);
	}



	//查询 我的订单
	public List<order> myOrder(String uid) throws SQLException {
		
		return orderDao.myOrder(uid);
	}



	//删除订单
	public void delOrder(String oid) throws SQLException {
		
		orderDao.delOrder(oid);
	}



	//删除所有订单
	public void delAll(String uid) throws SQLException {
		
		orderDao.delAll(uid);
		
	}

}
