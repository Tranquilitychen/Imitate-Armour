package dao;

import java.sql.SQLException;
import java.util.List;

import domain.pageBean;
import domain.product;
import domain.type;

public interface productDaointer {

	//index页面的type功能
	public List<type> indexType() throws SQLException;
	
	//productList功能1,  type_id为1,2,3,4,5的查询功能
	//查询多少个商品
	public Long countproduct_function_one(String pageBeanid) throws SQLException;
	//查询当前页面的Listproduct列表
	public List<product> productList_function_one(int currentPage,int currentTotal,String pageBeanid )throws SQLException;
	
	
	
	
	//productList功能2,  type_id为6的查询功能
	//多少个
	public Long countproduct_function_two() throws SQLException;
	//List
	public List<product> productList_function_two(int currentPage,int currentTotal) throws SQLException;
	
	
	
	////productList功能3,  type_id为7的查询功能
	//多少个
	public Long countproduct_function_three(String pattern) throws SQLException;
	//list
	public List<product> productList_function_three(int currentPage,int currentTotal,String pattern) throws SQLException;
	
	
	
	//productInfo
	public product productInfo(String pid)throws SQLException;
	
	
	
	// 在线搜索仿百度提示搜索功能
	public List<Object> onlineSearchlikeBaidu(String word)throws SQLException;
			
			
	
	
	
	
	
}
