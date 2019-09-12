package domain;

public class cartItem {

	
	private product product;
	private int cartItemCount;
	private int cartItemTotal;
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public int getCartItemCount() {
		return cartItemCount;
	}
	public void setCartItemCount(int cartItemCount) {
		this.cartItemCount = cartItemCount;
	}
	public int getCartItemTotal() {
		return cartItemTotal;
	}
	
	//设置总共的价格，让对象自动设置
	public void setAutoCartItemTotal() {
		
		//设置总共的价格
		if (product!=null) {
			cartItemTotal=cartItemCount*Integer.parseInt(product.getPprice());
		}
		
	}
	
	
	
	
	
	
}
