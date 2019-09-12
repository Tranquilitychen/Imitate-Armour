package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class cart {

	
	private int cartTotalmoney=0;
	
	//String ������Ʒ�� pid
	
	private Map<String, cartItem> map=new LinkedHashMap<String, cartItem>();

	public int getCartTotalmoney() {
		return cartTotalmoney;
	}

	
	public Map<String, cartItem> getMap() {
		return map;
	}

	//����cartitem���ܣ������Զ����ù��ﳵ���ܼ�
	public void addCartItem(cartItem cartItem){
		
		//��ӵ�����Ʒ�����ﳵ�����ж��ǲ����Ѿ����������Ʒ��
		
		//��ȡpid��
		String pid=cartItem.getProduct().getPid();
		
		//�ж��ǲ����Ѿ�����
		if (map.containsKey(pid)) {
			//����Ѿ�����
			
			//�õ�����Ҫ�ӵ���Ʒ������
			int willaddCount=cartItem.getCartItemCount();
			
			//����map���ϣ��������ü۸������
			for (Map.Entry<String, cartItem> entry:map.entrySet()) {
				
				//���������Ҫ��ӵ���Ʒ
				if (entry.getKey().equals(pid)) {
					
					//�õ�ԭ��Ҫ���������
					int oldCount=entry.getValue().getCartItemCount();
					
					//ɾ��ԭ���ļ۸�
					cartTotalmoney -=entry.getValue().getCartItemTotal();
					
					//����һ��Ҫ�������
					int newCount=oldCount+willaddCount;
					
					//��������cartitem�����ͼ۸�
					entry.getValue().setCartItemCount(newCount);
					entry.getValue().setAutoCartItemTotal();
					
					//��������cart����ļ۸�
					cartTotalmoney +=entry.getValue().getCartItemTotal();
				}
				
				
				
			}
			
			
			
			
		}else{
			//Ȼ�󲻴��ڣ�˵���ǵ�һ����ӣ�ֱ����ӵ�map
			map.put(pid, cartItem);
			cartTotalmoney +=cartItem.getCartItemTotal();
			
		}
		
		
		
	}
	
	public void delCart(String pid){
		
    //��ɾ���۸���ɾ��map�е�cartitem
		
		
		for (Map.Entry<String, cartItem> entry : map.entrySet()) {
			
			if (entry.getKey().equals(pid)) {
				
				cartTotalmoney -=entry.getValue().getCartItemTotal();
				
				
			}
			
			
		}
		
		map.remove(pid);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
