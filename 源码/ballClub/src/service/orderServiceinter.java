package service;

import java.sql.SQLException;
import java.util.List;

import domain.order;

public interface orderServiceinter {

	//�洢myorder
	public void addOrder(order order) throws SQLException;
	
	
	///����oid����order
	public order getOrder(String oid)throws SQLException;
	
	//����order�ɹ�����
	public void buyOrdersuccess(String address ,String oid) throws SQLException;
	//��ѯ�ҵĶ���
	public List<order> myOrder(String uid) throws SQLException;
		
	//ɾ������
	public void delOrder(String oid)throws SQLException;
	
	//ɾ�����ж���
	public void delAll(String uid)throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
}
