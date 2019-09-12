package domain;

public class orderItem {

	private String itemid;
	private product product;
	private order order;
	private int count;
	private int total_price;
	
	private String pid;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public order getOrder() {
		return order;
	}
	public void setOrder(order order) {
		this.order = order;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
