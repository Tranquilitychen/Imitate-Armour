package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;



import utils.sqlUtils;
import domain.order;
import domain.orderItem;
import domain.product;

public class orderDaoimpl implements orderDaointer{

	QueryRunner qr=new QueryRunner(sqlUtils.getDataSource());
	
	
	//存储myorder
	public void addOrder(order order) throws SQLException {
		
		
		
		//存储order
		//注意mysql中order是一个关键字，所以加上``
		String sql="insert into `order` value(?,?,?,?,?)";
		Object [] params={order.getOid(),order.getUser().getUid(),order.getAddress(),order.getState(),order.getTotal()};
		qr.update(sql, params);
		
		
		//存储orderitem
		List<orderItem> orderItems = order.getOrderItems();
		for (orderItem orderItem : orderItems) {
			String sqltwo="insert into orderitem value(?,?,?,?,?)";
			Object [] paramstwo={orderItem.getItemid(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid(),orderItem.getCount(),orderItem.getTotal_price()};
			
			qr.update(sqltwo,paramstwo);
			
		}
		
		
		
	}


	//购买order成功功能
	public void buyOrdersuccess(String address,String oid) throws SQLException {
		
		String sql="update `order` set address=? , state = ? where oid=?";
		
		qr.update(sql,address,1,oid);
		
		
	}


	//根据oid查询order
	public order getOrder(String oid) throws SQLException {
		
		String sql="select * from `order` where oid=?";
		//查询得到order
		order order=qr.query(sql, new BeanHandler<order>(order.class),oid);
		
		String sqltwo="select * from orderitem where oid=?";
		//查询orderitem
		List<orderItem> orderitems = qr.query(sqltwo, new BeanListHandler<orderItem>(orderItem.class),oid);
		//查询product封装到orderitem里面
		
		for (orderItem orderItem : orderitems) {
			
			String sqlthree="select * from product where pid= ?";
			product product = qr.query(sqlthree, new BeanHandler<product>(product.class),orderItem.getPid());
			orderItem.setProduct(product);
			
		}
		
		
		order.setOrderItems(orderitems);
		
		
		
		
		return order;
	}


	//查询我的订单
	public List<order> myOrder(String uid) throws SQLException {
		
		String sql="select * from `order` where uid=?";
		
		//查询orders
		List<order> orders=qr.query(sql, new BeanListHandler<order>(order.class),uid);
		
		//分别封装orderitem和product
		for (order order : orders) {
			//根据oid查询orderitem
			String sqltwo="select * from orderitem where oid=?";
			List<orderItem> orderitems = qr.query(sqltwo, new BeanListHandler<orderItem>(orderItem.class),order.getOid());
			
			//查询product
			for (orderItem orderItem : orderitems) {
				
				String sqlthree="select * from product where pid= ?";
				product product = qr.query(sqlthree, new BeanHandler<product>(product.class),orderItem.getPid());
				orderItem.setProduct(product);
				
			}
			
			order.setOrderItems(orderitems);
			
		}
		
		
		
		return orders;
	}


	//删除订单
	public void delOrder(String oid) throws SQLException {
		
		//删除order
		String sql="delete from `order` where oid=?";
		qr.update(sql,oid);
		
		
		//删除orderitem
		String sqltwo="delete from orderitem where oid=?";
		qr.update(sqltwo,oid);
		
	}


	//删除所有订单
	public void delAll(String uid) throws SQLException {
		
		
		//先查询出所有orders
		List<order> orders = myOrder(uid);
		
		//分别先根据oid删除orderitem
		for (order order : orders) {
			
			String oid=order.getOid();
			String sql="delete from orderitem where oid =?";
			qr.update(sql,oid);
			
		}
		
		//删除order
		String sqltwo="delete from `order` where uid= ?";
		qr.update(sqltwo,uid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
