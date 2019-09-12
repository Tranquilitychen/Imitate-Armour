package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.productDaoimpl;
import dao.productDaointer;
import domain.pageBean;
import domain.product;
import domain.type;

public class productServiceimpl  implements productServiceinter{

	productDaointer productDao=new productDaoimpl();
	
	//indexҳ���type����
	public List<type> indexType() throws SQLException {
		
		
		
		
		return productDao.indexType();
	}

	
	
	// productList����

	//idΪ��1,2,3,4,5�Ĳ�ѯ��ʽһ�����Զ���һ��List�б�id����list��������֣����������list�У������������
	private static List<String> pB_id=new ArrayList<String>();
	static {
		pB_id.add("1");
		pB_id.add("2");
		pB_id.add("3");
		pB_id.add("4");
		pB_id.add("5");
		
	}

	public pageBean productList(String pageBeanid, String currentPage_String,
			String pattern) throws SQLException {

		// �½�һ��Ҫ���ص�pageBean����
		pageBean pB = new pageBean();
		// ����һЩ�Ѿ�֪����ֵ
		pB.setPageBeanid(pageBeanid);

		pB.setPattern(pattern);
		// Ĭ��һҳ��ʾ9��
		pB.setCurrentTotal(9);
		// ��currentpage���int����
		if (currentPage_String == null) {
			// �����null��˵���ǵ�һ�η�����Ʒ�б�Ĭ���ǵ�һҳ
			currentPage_String = "1";
		}
		int currentPage = Integer.parseInt(currentPage_String);
		pB.setCurrentPage(currentPage);

		// ��ͬtype_id�в�ͬ�Ĵ���ʽ
		// �����1,2,3,4,5,���������ʽ
		if (pB_id.contains(pB.getPageBeanid())) {

			// ��ѯһ�����ٸ���Ʒ
			Long count = productDao.countproduct_function_one(pB
					.getPageBeanid());
			int total = count.intValue();
			pB.setTotal(total);
			// ��ѯ��ƷList�б�
			List<product> productList = productDao.productList_function_one(
					pB.getCurrentPage(), pB.getCurrentTotal(),
					pB.getPageBeanid());
			// limit ��ǰҳ��-1 * ÿҳ����
			pB.setProductList(productList);

			// һ������ҳ
			int totalPage = (int) Math.ceil(1.0 * total / pB.getCurrentTotal());
			pB.setTotalPage(totalPage);

		}

		// id��6�����,��ѯ����id
		if (pB.getPageBeanid().equals("6")) {

			// ��ѯһ�����ٸ���Ʒ
			Long total = productDao.countproduct_function_two();
			pB.setTotal(total.intValue());
			// ��ѯ��ƷList�б�
			List<product> productList = productDao.productList_function_two(
					pB.getCurrentPage(), pB.getCurrentTotal());

			pB.setProductList(productList);
			// һ������ҳ
			pB.setTotalPage((int) Math.ceil(1.0 * total.intValue()
					/ pB.getCurrentTotal()));

		}

		// id��7��˵������������,����3
		if (pB.getPageBeanid().equals("7")) {

			// ��ѯһ�����ٸ���Ʒ
			Long total = productDao.countproduct_function_three(pattern);
			pB.setTotal(total.intValue());
			// ��ѯ��ƷList�б�
			List<product> productList = productDao.productList_function_three(
					pB.getCurrentPage(), pB.getCurrentTotal(),pattern);

			pB.setProductList(productList);
			// һ������ҳ
			pB.setTotalPage((int) Math.ceil(1.0 * total.intValue()
					/ pB.getCurrentTotal()));

		}

		return pB;
	}

	//productInfo����
	public product productInfo(String pid) throws SQLException {
		
		product product = productDao.productInfo(pid);
		
		
		return product;
	}

	// ���������°ٶ���ʾ��������
	public List<Object> onlineSearchlikeBaidu(String word) throws SQLException {
		
		
		
		
		return productDao.onlineSearchlikeBaidu(word);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
