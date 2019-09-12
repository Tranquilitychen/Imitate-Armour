package service;

import java.sql.SQLException;
import java.util.List;

import dao.orderDaoimpl;
import dao.orderDaointer;
import domain.order;

public class orderServiceimpl implements orderServiceinter{

	orderDaointer orderDao=new orderDaoimpl();
	
	
	
	//�洢myorder
	public void addOrder(order order) throws SQLException {
		
		orderDao.addOrder(order);
	}



	//����order�ɹ�����
	public void buyOrdersuccess(String address,String oid) throws SQLException {
		
		orderDao.buyOrdersuccess(address,oid);
	}



	//����oid��ѯorder
	public order getOrder(String oid) throws SQLException {
		
		return orderDao.getOrder(oid);
	}



	//��ѯ �ҵĶ���
	public List<order> myOrder(String uid) throws SQLException {
		
		return orderDao.myOrder(uid);
	}



	//ɾ������
	public void delOrder(String oid) throws SQLException {
		
		orderDao.delOrder(oid);
	}



	//ɾ�����ж���
	public void delAll(String uid) throws SQLException {
		
		orderDao.delAll(uid);
		
	}

}
