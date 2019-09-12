package service;

import java.sql.SQLException;
import java.util.List;

import domain.pageBean;
import domain.product;
import domain.type;

public interface productServiceinter {


	//index页面的type功能
	public List<type> indexType() throws SQLException;
	
	//productList功能
	public pageBean productList(String pageBeanid,String currentPage_String,String pattern) throws SQLException;
	
	//productInfo
	public product productInfo(String pid)throws SQLException;
	
	// 在线搜索仿百度提示搜索功能
	public List<Object> onlineSearchlikeBaidu(String word)throws SQLException;
	
}
