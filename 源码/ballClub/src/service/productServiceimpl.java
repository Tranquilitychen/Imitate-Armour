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
	
	//index页面的type功能
	public List<type> indexType() throws SQLException {
		
		
		
		
		return productDao.indexType();
	}

	
	
	// productList功能

	//id为，1,2,3,4,5的查询方式一样，自定义一个List列表，id对弈list里面的数字，如果存在在list中，就用这个方法
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

		// 新建一个要返回的pageBean对象
		pageBean pB = new pageBean();
		// 设置一些已经知道的值
		pB.setPageBeanid(pageBeanid);

		pB.setPattern(pattern);
		// 默认一页显示9个
		pB.setCurrentTotal(9);
		// 把currentpage变成int类型
		if (currentPage_String == null) {
			// 如果是null，说明是第一次访问商品列表，默认是第一页
			currentPage_String = "1";
		}
		int currentPage = Integer.parseInt(currentPage_String);
		pB.setCurrentPage(currentPage);

		// 不同type_id有不同的处理方式
		// 如果是1,2,3,4,5,用这个处理方式
		if (pB_id.contains(pB.getPageBeanid())) {

			// 查询一个多少个商品
			Long count = productDao.countproduct_function_one(pB
					.getPageBeanid());
			int total = count.intValue();
			pB.setTotal(total);
			// 查询商品List列表
			List<product> productList = productDao.productList_function_one(
					pB.getCurrentPage(), pB.getCurrentTotal(),
					pB.getPageBeanid());
			// limit 当前页面-1 * 每页数量
			pB.setProductList(productList);

			// 一共多少页
			int totalPage = (int) Math.ceil(1.0 * total / pB.getCurrentTotal());
			pB.setTotalPage(totalPage);

		}

		// id是6的情况,查询所有id
		if (pB.getPageBeanid().equals("6")) {

			// 查询一个多少个商品
			Long total = productDao.countproduct_function_two();
			pB.setTotal(total.intValue());
			// 查询商品List列表
			List<product> productList = productDao.productList_function_two(
					pB.getCurrentPage(), pB.getCurrentTotal());

			pB.setProductList(productList);
			// 一共多少页
			pB.setTotalPage((int) Math.ceil(1.0 * total.intValue()
					/ pB.getCurrentTotal()));

		}

		// id是7，说明是在线搜索,功能3
		if (pB.getPageBeanid().equals("7")) {

			// 查询一个多少个商品
			Long total = productDao.countproduct_function_three(pattern);
			pB.setTotal(total.intValue());
			// 查询商品List列表
			List<product> productList = productDao.productList_function_three(
					pB.getCurrentPage(), pB.getCurrentTotal(),pattern);

			pB.setProductList(productList);
			// 一共多少页
			pB.setTotalPage((int) Math.ceil(1.0 * total.intValue()
					/ pB.getCurrentTotal()));

		}

		return pB;
	}

	//productInfo功能
	public product productInfo(String pid) throws SQLException {
		
		product product = productDao.productInfo(pid);
		
		
		return product;
	}

	// 在线搜索仿百度提示搜索功能
	public List<Object> onlineSearchlikeBaidu(String word) throws SQLException {
		
		
		
		
		return productDao.onlineSearchlikeBaidu(word);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
