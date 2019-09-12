package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.sqlUtils;
import domain.pageBean;
import domain.product;
import domain.type;

public class productDaoimpl implements productDaointer{

	QueryRunner qr=new QueryRunner(sqlUtils.getDataSource());
	
	//index页面的type功能
	public List<type> indexType() throws SQLException {

		String sql="select * from type";

		List<type> list = qr.query(sql, new BeanListHandler<type>(type.class));
		
		return list;
	}

	//productList功能1,  type_id 为1,2,3,4,5
	public Long countproduct_function_one(String pageBeanid) throws SQLException {
		
		String sql="select count(*) from product where type_id=?";
		Long qLong=(Long) qr.query(sql, new ScalarHandler(),pageBeanid);
		
		
		return qLong;
	}

	
	//查询当前页面的Listproduct列表
	public List<product> productList_function_one(int currentPage,
			int currentTotal ,String pageBeanid) throws SQLException {
		
		String sql="select * from product where type_id=? limit ?,?";
		
		
		return qr.query(sql, new BeanListHandler<product>(product.class),pageBeanid,(currentPage-1)*currentTotal,currentTotal);                  
	}

	//productList功能2,  type_id 为6
	public Long countproduct_function_two() throws SQLException {
		
		String sql="select count(*) from product";
		Long query = (Long) qr.query(sql, new ScalarHandler());
		
		return query;
	}

	//List
	public List<product> productList_function_two(int currentPage,
			int currentTotal) throws SQLException {
		
		String sql="select * from product limit ?,?";
		
		
		return qr.query(sql, new BeanListHandler<product>(product.class),(currentPage-1)*currentTotal,currentTotal);
	}

	//productList功能3，在线搜索功能，多少个
	public Long countproduct_function_three(String pattern) throws SQLException {

		String sql="select count(*) from product where pname like ?";
		
		
		return (Long)qr.query(sql, new ScalarHandler(),"%"+pattern+"%");
	}

	//productList功能3，在线搜索功能，list功能
	public List<product> productList_function_three(int currentPage,
			int currentTotal, String pattern) throws SQLException {
		
		
		String sql="select * from product where pname like ? limit ?,?";
		
		
		return qr.query(sql, new BeanListHandler<product>(product.class),"%"+pattern+"%",(currentPage-1)*currentTotal,currentTotal);
		
	}

	//productInfo功能
	public product productInfo(String pid) throws SQLException {
		
		String sql="select * from product where pid=?";
		
		
		
		return qr.query(sql, new BeanHandler<product>(product.class),pid);
	}

	
		
			
	// 在线搜索仿百度提示搜索功能
	public List<Object> onlineSearchlikeBaidu(String word) throws SQLException {
		
		String sql="select * from product where pname like ?";
		
		
		return qr.query(sql, new ColumnListHandler("pname"),"%"+word+"%");
	}

	
	

	
	
	
}
