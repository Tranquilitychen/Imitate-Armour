package dao;

import java.sql.SQLException;
import java.util.List;

import domain.product;

public interface adminDaointer {

	// indexҳ����ʾ��Ʒ����
	public List<product> indexList() throws SQLException;

	// �����Ʒ
	public void addproduct(product product) throws SQLException;
	//ɾ����Ʒ
	public void delproduct(String pid ) throws SQLException;
}
