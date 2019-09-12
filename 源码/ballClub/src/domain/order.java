package domain;

import java.util.ArrayList;
import java.util.List;

public class order {

	private String oid;
	private user user;
	private String address;
	private int state;
	private int total;
	
	private List<orderItem> orderItems=new ArrayList<orderItem>();
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<orderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<orderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
