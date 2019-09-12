package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class sqlUtils {

	private static  ComboPooledDataSource dataSource=new ComboPooledDataSource();
	private static  ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	public static ComboPooledDataSource getDataSource(){
		
		return dataSource;
		
	}
	public static Connection getConnection() throws SQLException{
		
		return dataSource.getConnection();
		
	}
	public static Connection getcurrentConnection() throws SQLException{
		Connection con =tl.get();
		if (con==null) {
			con=getConnection();
			tl.set(con);
		}
		return con;
		
	}
	public static void starttransaction() throws SQLException{
		Connection con = getcurrentConnection();
		con.setAutoCommit(false);
		
	}
	
	
	public static void rollback() throws SQLException{
		Connection con = getcurrentConnection();
		con.rollback();
		tl.remove();
		con.close();

	}
	public static void commit() throws SQLException{
		Connection con = getcurrentConnection();
		con.commit();
		tl.remove();
		con.close();
		
		
	}
	
	
}
