package domain;

import java.util.List;

public class pageBean {

	//��ҳ��
	
	//����indextype �ж�����࣬ÿһ���������в�ͬ���͵���Ʒ�����Ը���
	//indextype�Ĳ�ͬ��ҳ����ʾ����Ʒ���ͬ����������һ��������ԣ���Ӧindextype�е�type_id
	
	//��ѯָ����Ʒ���͵�id����Ӧtype��type_id
	// 1  ����װ��
	// 2 hot��Ʒ
	// 3 ����ѵ��
	private String pageBeanid;
	
	//Ҫ��ѯ����Ʒһ���ж��ٸ�
	private int total;
	
	//Ҫ��ѯ����Ʒһҳ��ʾ���ٸ�
	private int currentTotal;
	
	//��ǰ�ǵڼ�ҳ
	private int currentPage;
	
	//һ������ҳ
	private int totalPage;
	
	//ÿһҳ��ʾ����ƷList
	private List<product> productList;
//��������������
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
