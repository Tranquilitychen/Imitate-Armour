package dao;

import java.sql.SQLException;
import java.util.List;

import domain.type;
import domain.user;

public interface userDaointer {

	//用户登录
	public user login(String username,String password) throws SQLException;
	
	
	//校验用户注册登录名是否存在
	public Long isExistloginName(String name) throws SQLException;
	
	//注册功能
	public void regist(user user) throws SQLException;
	
	//邮箱激活账号功能
	public void active(String active) throws SQLException;
	
	
		
		
		
		
		
		
		
		
		
		
		
}
