package service;

import java.sql.SQLException;
import java.util.List;

import domain.pageBean;
import domain.product;
import domain.type;

public interface productServiceinter {


	//indexҳ���type����
	public List<type> indexType() throws SQLException;
	
	//productList����
	public pageBean productList(String pageBeanid,String currentPage_String,String pattern) throws SQLException;
	
	//productInfo
	public product productInfo(String pid)throws SQLException;
	
	// ���������°ٶ���ʾ��������
	public List<Object> onlineSearchlikeBaidu(String word)throws SQLException;
	
}
