package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class cart {

	
	private int cartTotalmoney=0;
	
	//String 就是商品的 pid
	
	private Map<String, cartItem> map=new LinkedHashMap<String, cartItem>();

	public int getCartTotalmoney() {
		return cartTotalmoney;
	}

	
	public Map<String, cartItem> getMap() {
		return map;
	}

	//增加cartitem功能，并且自动设置购物车的总价
	public void addCartItem(cartItem cartItem){
		
		//添加单个商品到购物车，先判断是不是已经存在这个商品，
		
		//获取pid，
		String pid=cartItem.getProduct().getPid();
		
		//判断是不是已经存在
		if (map.containsKey(pid)) {
			//如果已经存在
			
			//得到现在要加的商品的数量
			int willaddCount=cartItem.getCartItemCount();
			
			//遍历map集合，重新设置价格和数量
			for (Map.Entry<String, cartItem> entry:map.entrySet()) {
				
				//如果是我们要添加的商品
				if (entry.getKey().equals(pid)) {
					
					//得到原来要购买的数量
					int oldCount=entry.getValue().getCartItemCount();
					
					//删除原来的价格
					cartTotalmoney -=entry.getValue().getCartItemTotal();
					
					//现在一共要买的数量
					int newCount=oldCount+willaddCount;
					
					//重新设置cartitem数量和价格
					entry.getValue().setCartItemCount(newCount);
					entry.getValue().setAutoCartItemTotal();
					
					//重新设置cart里面的价格
					cartTotalmoney +=entry.getValue().getCartItemTotal();
				}
				
				
				
			}
			
			
			
			
		}else{
			//然后不存在，说明是第一层添加，直接添加到map
			map.put(pid, cartItem);
			cartTotalmoney +=cartItem.getCartItemTotal();
			
		}
		
		
		
	}
	
	public void delCart(String pid){
		
    //先删除价格，在删除map中的cartitem
		
		
		for (Map.Entry<String, cartItem> entry : map.entrySet()) {
			
			if (entry.getKey().equals(pid)) {
				
				cartTotalmoney -=entry.getValue().getCartItemTotal();
				
				
			}
			
			
		}
		
		map.remove(pid);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
