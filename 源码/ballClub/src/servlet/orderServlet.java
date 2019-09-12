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
		
		//获取cart
		cart cart = (cart) session.getAttribute("cart");
		//获取user
		user user = (user) session.getAttribute("user");
		
		//创建order，设置一些初始化信息
		order order=new order();
		order.setState(0);
		order.setOid(myUtils.getUUID());
		order.setAddress(null);
		order.setUser(user);
		//订单总价就是购物车的总价
		order.setTotal(cart.getCartTotalmoney());
		
		//封装orderitem，每一个orderitem就是cartitem
		Map<String, cartItem> cart_map = cart.getMap();
		for (Map.Entry<String, cartItem> entry : cart_map.entrySet()) {
			
			cartItem cartItem = entry.getValue();
			
			//创建orderitem
			orderItem orderItem= new orderItem();
			orderItem.setCount(cartItem.getCartItemCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setItemid(myUtils.getUUID());
			orderItem.setOrder(order);
			orderItem.setTotal_price(cartItem.getCartItemTotal());
			order.getOrderItems().add(orderItem);
			
			
		}
		
		//存储到数据库
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
	//下订单
	public  void buyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String money = request.getParameter("money");
		String orderId = request.getParameter("orderId");
		String orderTotal = request.getParameter("orderTotal");
		String address = request.getParameter("address");
		
		//根据oid查找这个订单
		order order=null;
		try {
			order=orderService.getOrder(orderId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//判断钱是不是足够
		if (Integer.parseInt(money)<Integer.parseInt(orderTotal)) {
			//钱不够，返回
			request.setAttribute("order", order);
			request.setAttribute("orderWrong", "钱不够");
			request.getRequestDispatcher("user/buyOrder.jsp").forward(request, response);
			
		}else{
			
			//修改订单状态0   1
			//增加地址
			try {
				orderService.buyOrdersuccess(address, orderId);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath()+"/user/buyorderSuccess.jsp");
			
		}
		
		
		
	}
	
	//我的订单查询
	public  void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//根据user查询orders
		
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
	
	
	// 在查询所有订单中购买单个订单
	public void buyOrderinMyorders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oid = request.getParameter("oid");

		// 根据oid查找这个订单
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
	
	// 删除订单
	public void delOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//删除订单操作
		String oid = request.getParameter("oid");

		try {
			orderService.delOrder(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//删除完毕，再次查询所有订单，并且返回到我的订单jsp页面
		// 根据user查询orders

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
	
	// 删除所有订单
	public void delAll(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		user user = (user)session.getAttribute("user");
		
		//删除
		try {
			orderService.delAll(user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//再次查询所有订单
		

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
