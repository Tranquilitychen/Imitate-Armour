package dao;

import java.sql.SQLException;
import java.util.List;

import domain.type;
import domain.user;

public interface userDaointer {

	//�û���¼
	public user login(String username,String password) throws SQLException;
	
	
	//У���û�ע���¼���Ƿ����
	public Long isExistloginName(String name) throws SQLException;
	
	//ע�Ṧ��
	public void regist(user user) throws SQLException;
	
	//���伤���˺Ź���
	public void active(String active) throws SQLException;
	
	
		
		
		
		
		
		
		
		
		
		
		
}
