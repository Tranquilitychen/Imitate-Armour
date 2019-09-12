package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.cart;
import domain.cartItem;
import domain.product;
import service.cartServiceimpl;
import service.cartServiceinter;
import service.productServiceimpl;
import service.productServiceinter;

public class cartServlet extends baseServlet {
	
	productServiceinter productService=new productServiceimpl();
	cartServiceinter cartService=new cartServiceimpl();
	
	//��ӵ�����Ʒ�����ﳵ
	public  void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		String count = request.getParameter("count");
		HttpSession session = request.getSession();
		
		
		//��ѯҪ��ӵ����ﳵ����Ʒ
		product product=null;
		try {
			 product = productService.productInfo(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ӵ�cartitem
		cartItem cartItem=new cartItem();
		cartItem.setProduct(product);
		cartItem.setCartItemCount(Integer.parseInt(count));
		cartItem.setAutoCartItemTotal();
		
		
		//��ӵ�cart
		
		
		//����ǵ�һ�����
		cart cart = (cart) session.getAttribute("cart");
		if (cart==null) {
			cart=new cart();
		}
		
		cart.addCartItem(cartItem);
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
		
	}

	//ɾ��������Ʒ
	public  void delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		//System.out.println(pid);
		HttpSession session = request.getSession();
		
		cart cart = (cart) session.getAttribute("cart");
		
		
	
		cart.delCart(pid);
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
		
	}
	
	//��չ��ﳵ
	public  void delAllcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getSession().removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
	
	
	
	}
	
	
	
	
	
	
	
	
	
}
