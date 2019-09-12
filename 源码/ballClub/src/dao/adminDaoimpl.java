package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.sqlUtils;
import domain.product;

public class adminDaoimpl implements adminDaointer{

	QueryRunner qr=new QueryRunner(sqlUtils.getDataSource());
	
	//index页面显示商品功能
	public List<product> indexList() throws SQLException {
		
		String sql="select * from product";
		
		return qr.query(sql, new BeanListHandler<product>(product.class));
	}

	// 添加商品
	public void addproduct(product product) throws SQLException {
		
		String sql="insert into product value (?,?,?,?,?,?)";
	
		qr.update(sql,product.getPid(),product.getPname(),product.getPprice(),product.getPdesc(),product.getType_id(),product.getPimg());
		
	}

	//删除商品
	public void delproduct(String pid) throws SQLException {
		
		String sql="delete from product where pid= ?";
		qr.update(sql,pid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
