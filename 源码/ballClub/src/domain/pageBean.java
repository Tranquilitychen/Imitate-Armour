package domain;

import java.util.List;

public class pageBean {

	//分页类
	
	//由于indextype 有多个分类，每一个分类又有不同类型的商品，所以根据
	//indextype的不同，页面显示的商品类别不同，所以增加一个类别属性，对应indextype中的type_id
	
	//查询指定商品类型的id，对应type中type_id
	// 1  男子装备
	// 2 hot商品
	// 3 篮球训练
	private String pageBeanid;
	
	//要查询的商品一共有多少个
	private int total;
	
	//要查询的商品一页显示多少个
	private int currentTotal;
	
	//当前是第几页
	private int currentPage;
	
	//一共多少页
	private int totalPage;
	
	//每一页显示的商品List
	private List<product> productList;
//在线搜索的属性
	private String pattern;
	
	public String getPageBeanid() {
		return pageBeanid;
	}

	public void setPageBeanid(String pageBeanid) {
		this.pageBeanid = pageBeanid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentTotal() {
		return currentTotal;
	}

	public void setCurrentTotal(int currentTotal) {
		this.currentTotal = currentTotal;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<product> getProductList() {
		return productList;
	}

	public void setProductList(List<product> productList) {
		this.productList = productList;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
