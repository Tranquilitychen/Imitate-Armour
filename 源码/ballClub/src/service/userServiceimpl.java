package service;

import java.sql.SQLException;

import dao.userDaoimpl;
import dao.userDaointer;
import domain.user;

public class userServiceimpl implements userServiceinter{

	userDaointer userDao = new userDaoimpl();
	
	//�û���¼
	@Override
	public user login(String username, String password) throws SQLException {
		
		
		
		return userDao.login(username, password);
	}

	//У���û�ע���¼���Ƿ����
	public boolean isExistloginName(String name) throws SQLException {
		
		Long isExistloginName=userDao.isExistloginName(name);
		
		
		
		
		return isExistloginName>0?true:false;
	}

	//ע�Ṧ��
	public void regist(user user) throws SQLException {
		
		
		userDao.regist(user);
		
		
	}

	//���伤���˺Ź���
	public void active(String active) throws SQLException {
		
		userDao.active(active);
		
	}

	
	
	

	
	
	
}
