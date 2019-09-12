package service;

import java.sql.SQLException;
import java.util.List;

import domain.order;

public interface orderServiceinter {

	//存储myorder
	public void addOrder(order order) throws SQLException;
	
	
	///根据oid查找order
	public order getOrder(String oid)throws SQLException;
	
	//购买order成功功能
	public void buyOrdersuccess(String address ,String oid) throws SQLException;
	//查询我的订单
	public List<order> myOrder(String uid) throws SQLException;
		
	//删除订单
	public void delOrder(String oid)throws SQLException;
	
	//删除所有订单
	public void delAll(String uid)throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
}
