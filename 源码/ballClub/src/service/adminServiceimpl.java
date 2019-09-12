package service;

import java.sql.SQLException;
import java.util.List;

import dao.adminDaoimpl;
import dao.adminDaointer;
import domain.product;

public class adminServiceimpl implements adminServiceinter {

	adminDaointer adminDao=new adminDaoimpl();
	
	
	
	//index页面显示商品功能
	public List<product> indexList() throws SQLException {
		
		return adminDao.indexList();
	}



	// 添加商品
	public void addproduct(product product) throws SQLException {
		
		adminDao.addproduct(product);
		
	}



	//删除商品
	public void delproduct(String pid) throws SQLException {
		
		adminDao.delproduct(pid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
