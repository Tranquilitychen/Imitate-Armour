package service;

import java.sql.SQLException;
import java.util.List;

import dao.adminDaoimpl;
import dao.adminDaointer;
import domain.product;

public class adminServiceimpl implements adminServiceinter {

	adminDaointer adminDao=new adminDaoimpl();
	
	
	
	//indexҳ����ʾ��Ʒ����
	public List<product> indexList() throws SQLException {
		
		return adminDao.indexList();
	}



	// �����Ʒ
	public void addproduct(product product) throws SQLException {
		
		adminDao.addproduct(product);
		
	}



	//ɾ����Ʒ
	public void delproduct(String pid) throws SQLException {
		
		adminDao.delproduct(pid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
