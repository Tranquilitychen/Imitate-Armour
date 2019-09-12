package service;

import java.sql.SQLException;
import java.util.List;

import domain.product;

public interface adminServiceinter {

	
	//index页面显示商品功能
	public List<product> indexList() throws SQLException;
	
	//添加商品
	public void addproduct(product product) throws SQLException;
	
	//删除商品
	public void delproduct(String pid ) throws SQLException;
	
}
