package dao;

import java.sql.SQLException;
import java.util.List;

import domain.pageBean;
import domain.product;
import domain.type;

public interface productDaointer {

	//indexҳ���type����
	public List<type> indexType() throws SQLException;
	
	//productList����1,  type_idΪ1,2,3,4,5�Ĳ�ѯ����
	//��ѯ���ٸ���Ʒ
	public Long countproduct_function_one(String pageBeanid) throws SQLException;
	//��ѯ��ǰҳ���Listproduct�б�
	public List<product> productList_function_one(int currentPage,int currentTotal,String pageBeanid )throws SQLException;
	
	
	
	
	//productList����2,  type_idΪ6�Ĳ�ѯ����
	//���ٸ�
	public Long countproduct_function_two() throws SQLException;
	//List
	public List<product> productList_function_two(int currentPage,int currentTotal) throws SQLException;
	
	
	
	////productList����3,  type_idΪ7�Ĳ�ѯ����
	//���ٸ�
	public Long countproduct_function_three(String pattern) throws SQLException;
	//list
	public List<product> productList_function_three(int currentPage,int currentTotal,String pattern) throws SQLException;
	
	
	
	//productInfo
	public product productInfo(String pid)throws SQLException;
	
	
	
	// ���������°ٶ���ʾ��������
	public List<Object> onlineSearchlikeBaidu(String word)throws SQLException;
			
			
	
	
	
	
	
}
