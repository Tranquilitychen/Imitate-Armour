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
	
	
	//�洢myorder
	public void addOrder(order order) throws SQLException {
		
		
		
		//�洢order
		//ע��mysql��order��һ���ؼ��֣����Լ���``
		String sql="insert into `order` value(?,?,?,?,?)";
		Object [] params={order.getOid(),order.getUser().getUid(),order.getAddress(),order.getState(),order.getTotal()};
		qr.update(sql, params);
		
		
		//�洢orderitem
		List<orderItem> orderItems = order.getOrderItems();
		for (orderItem orderItem : orderItems) {
			String sqltwo="insert into orderitem value(?,?,?,?,?)";
			Object [] paramstwo={orderItem.getItemid(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid(),orderItem.getCount(),orderItem.getTotal_price()};
			
			qr.update(sqltwo,paramstwo);
			
		}
		
		
		
	}


	//����order�ɹ�����
	public void buyOrdersuccess(String address,String oid) throws SQLException {
		
		String sql="update `order` set address=? , state = ? where oid=?";
		
		qr.update(sql,address,1,oid);
		
		
	}


	//����oid��ѯorder
	public order getOrder(String oid) throws SQLException {
		
		String sql="select * from `order` where oid=?";
		//��ѯ�õ�order
		order order=qr.query(sql, new BeanHandler<order>(order.class),oid);
		
		String sqltwo="select * from orderitem where oid=?";
		//��ѯorderitem
		List<orderItem> orderitems = qr.query(sqltwo, new BeanListHandler<orderItem>(orderItem.class),oid);
		//��ѯproduct��װ��orderitem����
		
		for (orderItem orderItem : orderitems) {
			
			String sqlthree="select * from product where pid= ?";
			product product = qr.query(sqlthree, new BeanHandler<product>(product.class),orderItem.getPid());
			orderItem.setProduct(product);
			
		}
		
		
		order.setOrderItems(orderitems);
		
		
		
		
		return order;
	}


	//��ѯ�ҵĶ���
	public List<order> myOrder(String uid) throws SQLException {
		
		String sql="select * from `order` where uid=?";
		
		//��ѯorders
		List<order> orders=qr.query(sql, new BeanListHandler<order>(order.class),uid);
		
		//�ֱ��װorderitem��product
		for (order order : orders) {
			//����oid��ѯorderitem
			String sqltwo="select * from orderitem where oid=?";
			List<orderItem> orderitems = qr.query(sqltwo, new BeanListHandler<orderItem>(orderItem.class),order.getOid());
			
			//��ѯproduct
			for (orderItem orderItem : orderitems) {
				
				String sqlthree="select * from product where pid= ?";
				product product = qr.query(sqlthree, new BeanHandler<product>(product.class),orderItem.getPid());
				orderItem.setProduct(product);
				
			}
			
			order.setOrderItems(orderitems);
			
		}
		
		
		
		return orders;
	}


	//ɾ������
	public void delOrder(String oid) throws SQLException {
		
		//ɾ��order
		String sql="delete from `order` where oid=?";
		qr.update(sql,oid);
		
		
		//ɾ��orderitem
		String sqltwo="delete from orderitem where oid=?";
		qr.update(sqltwo,oid);
		
	}


	//ɾ�����ж���
	public void delAll(String uid) throws SQLException {
		
		
		//�Ȳ�ѯ������orders
		List<order> orders = myOrder(uid);
		
		//�ֱ��ȸ���oidɾ��orderitem
		for (order order : orders) {
			
			String oid=order.getOid();
			String sql="delete from orderitem where oid =?";
			qr.update(sql,oid);
			
		}
		
		//ɾ��order
		String sqltwo="delete from `order` where uid= ?";
		qr.update(sqltwo,uid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
