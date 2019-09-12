package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.sqlUtils;
import domain.type;
import domain.user;

public class userDaoimpl implements userDaointer{

	QueryRunner qr=new QueryRunner(sqlUtils.getDataSource());
	
	//�û���¼
	@Override
	public user login(String username, String password) throws SQLException {
		
		String sql="select * from user where name=? and password= ?";
		
		user user = qr.query(sql, new BeanHandler<user>(user.class),username,password);
		
		return user;
		
		
	}

	//У���û�ע���¼���Ƿ����
	public Long isExistloginName(String name) throws SQLException {
		
		
		String sql="select count(*) from user where name=?";
		
		Long isExistloginName = (Long) qr.query(sql, new ScalarHandler(),name);
		
		
		return isExistloginName;
	}

	//ע�Ṧ��
	public void regist(user user) throws SQLException {
		
		
		String sql="insert into user value(?,?,?,?,?,?,?,?,?)";
		
		qr.update(sql,user.getUid(),user.getName(),user.getPassword(),
				user.getSex(),user.getEmail(),user.getAddress(),user.getTelephone(),user.getState(),user.getActive());
		
		
		
	}

	//���伤���˺Ź���
	public void active(String active) throws SQLException {
		
		String sql="update user set state=? where active=?";
		
		qr.update(sql,1,active);
		
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
