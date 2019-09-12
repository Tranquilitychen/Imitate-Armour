package service;

import java.sql.SQLException;

import dao.userDaoimpl;
import dao.userDaointer;
import domain.user;

public class userServiceimpl implements userServiceinter{

	userDaointer userDao = new userDaoimpl();
	
	//用户登录
	@Override
	public user login(String username, String password) throws SQLException {
		
		
		
		return userDao.login(username, password);
	}

	//校验用户注册登录名是否存在
	public boolean isExistloginName(String name) throws SQLException {
		
		Long isExistloginName=userDao.isExistloginName(name);
		
		
		
		
		return isExistloginName>0?true:false;
	}

	//注册功能
	public void regist(user user) throws SQLException {
		
		
		userDao.regist(user);
		
		
	}

	//邮箱激活账号功能
	public void active(String active) throws SQLException {
		
		userDao.active(active);
		
	}

	
	
	

	
	
	
}
