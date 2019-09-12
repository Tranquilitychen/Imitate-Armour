package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.cart;
import domain.cartItem;
import domain.order;
import domain.orderItem;
import domain.user;
import service.orderServiceimpl;
import service.orderServiceinter;
import utils.myUtils;

public class orderServlet extends baseServlet {
	
	orderServiceinter orderService=new orderServiceimpl();
	
	
	public  void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//��ȡcart
		cart cart = (cart) session.getAttribute("cart");
		//��ȡuser
		user user = (user) session.getAttribute("user");
		
		//����order������һЩ��ʼ����Ϣ
		order order=new order();
		order.setState(0);
		order.setOid(myUtils.getUUID());
		order.setAddress(null);
		order.setUser(user);
		//�����ܼ۾��ǹ��ﳵ���ܼ�
		order.setTotal(cart.getCartTotalmoney());
		
		//��װorderitem��ÿһ��orderitem����cartitem
		Map<String, cartItem> cart_map = cart.getMap();
		for (Map.Entry<String, cartItem> entry : cart_map.entrySet()) {
			
			cartItem cartItem = entry.getValue();
			
			//����orderitem
			orderItem orderItem= new orderItem();
			orderItem.setCount(cartItem.getCartItemCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setItemid(myUtils.getUUID());
			orderItem.setOrder(order);
			orderItem.setTotal_price(cartItem.getCartItemTotal());
			order.getOrderItems().add(orderItem);
			
			
		}
		
		//�洢�����ݿ�
		try {
			orderService.addOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.removeAttribute("cart");
		request.setAttribute("order", order);
		request.getRequestDispatcher("/user/buyOrder.jsp").forward(request, response);
		
	}
	//�¶���
	public  void buyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String money = request.getParameter("money");
		String orderId = request.getParameter("orderId");
		String orderTotal = request.getParameter("orderTotal");
		String address = request.getParameter("address");
		
		//����oid�����������
		order order=null;
		try {
			order=orderService.getOrder(orderId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//�ж�Ǯ�ǲ����㹻
		if (Integer.parseInt(money)<Integer.parseInt(orderTotal)) {
			//Ǯ����������
			request.setAttribute("order", order);
			request.setAttribute("orderWrong", "Ǯ����");
			request.getRequestDispatcher("user/buyOrder.jsp").forward(request, response);
			
		}else{
			
			//�޸Ķ���״̬0   1
			//���ӵ�ַ
			try {
				orderService.buyOrdersuccess(address, orderId);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath()+"/user/buyorderSuccess.jsp");
			
		}
		
		
		
	}
	
	//�ҵĶ�����ѯ
	public  void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//����user��ѯorders
		
		HttpSession session = request.getSession();
		user user=(user)session.getAttribute("user");
		
		List<order> myOrders=null;
		
		try {
			myOrders = orderService.myOrder(user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("myOrders", myOrders);
		request.getRequestDispatcher("/user/orders.jsp").forward(request, response);
		
	}
	
	
	// �ڲ�ѯ���ж����й��򵥸�����
	public void buyOrderinMyorders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oid = request.getParameter("oid");

		// ����oid�����������
		order order = null;
		try {
			order = orderService.getOrder(oid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("order", order);
		request.getRequestDispatcher("user/buyOrder.jsp").forward(request, response);
		
		
	}
	
	// ɾ������
	public void delOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//ɾ����������
		String oid = request.getParameter("oid");

		try {
			orderService.delOrder(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//ɾ����ϣ��ٴβ�ѯ���ж��������ҷ��ص��ҵĶ���jspҳ��
		// ����user��ѯorders

		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");

		List<order> myOrders = null;

		try {
			myOrders = orderService.myOrder(user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("myOrders", myOrders);
		request.getRequestDispatcher("/user/orders.jsp").forward(request,
				response);

	}
	
	// ɾ�����ж���
	public void delAll(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		user user = (user)session.getAttribute("user");
		
		//ɾ��
		try {
			orderService.delAll(user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�ٴβ�ѯ���ж���
		

		List<order> myOrders = null;

		try {
			myOrders = orderService.myOrder(user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("myOrders", myOrders);
		request.getRequestDispatcher("/user/orders.jsp").forward(request,
				response);

	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
