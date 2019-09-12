package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import domain.pageBean;
import domain.product;
import domain.type;
import domain.user;
import service.productServiceimpl;
import service.productServiceinter;


public class productServlet extends baseServlet {
	
	productServiceinter productService=new productServiceimpl();
	
	//首页type显示
	public void indexType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//查询type
		List<type> list =null;
		try {
			 list = productService.indexType();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//由于是ajax，返回值是一个json数组
		
		Gson Gson=new Gson();
		String json = Gson.toJson(list);
		//System.out.println(json);
		//返回值
		response.getWriter().write(json);
		
		
		
	}

	// productList页面的功能显示
	public void productList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//测试代码
		//System.out.println(request.getParameter("pageBeanid"));
		
		//获取参数
		String pageBeanid = request.getParameter("pageBeanid");
		String currentPage_String = request.getParameter("currentPage");
		
		//在线搜索,为了避免new String出现空指针的情况做处理
		String pattern = request.getParameter("name");
		if (pattern!=null) {
			 pattern=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		}else{
			pattern="";
		}

		
		pageBean pB=new pageBean();
		//得到一个pageBean对象
		try {
			pB=productService.productList(pageBeanid, currentPage_String,pattern);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//测试代码
		//System.out.println(pB.getProductList());
		//传输到页面中
		request.setAttribute("pageBean", pB);
		
		//浏览历史记录功能
		List<product> hostpro=new ArrayList<product>();
		Cookie[] cookies=request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids=cookie.getValue();
					//切割pids
					String [] split=pids.split("-");
					//分别遍历查询product
					for (String string : split) {
						product product=null;
						try {
							 product=productService.productInfo(string);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						hostpro.add(product);
					}
					
					
				}
			}
		}
		
		request.setAttribute("hostpro", hostpro);
		
		
		
		
		
		request.getRequestDispatcher("/user/productList.jsp").forward(request, response);

		
		
	}

	//在线搜索功能
	public void onlineSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//也是分页功能，调用productlist功能
		productList(request, response);
		
		
		

	}

	// productInfo功能,单个商品信息显示
	public void productInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		String pageBeanid = request.getParameter("pageBeanid");

		// 为了避免new String出现空指针的情况做处理
		String name = request.getParameter("name");
		if (name != null) {
			name = new String(request.getParameter("name").getBytes(
					"ISO-8859-1"), "UTF-8");
		} else {
			name = "";
		}

		product product = new product();

		// 查找商品
		try {
			product = productService.productInfo(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 浏览历史记录功能
		// 123-13-13123-12312的显示存储到cookie里面，来实现这个功能
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		// 如果原来就已经存在，要进行加工处理
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("pids")) {

					pids = cookie.getValue();
					// 分隔
					String[] split = pids.split("-");
					// 转化为list集合
					List<String> list = Arrays.asList(split);
					// 转化为linked方便操作
					LinkedList<String> linkedlist = new LinkedList<String>(list);
					if (linkedlist.contains(pid)) {
						linkedlist.remove(pid);

					}
					linkedlist.addFirst(pid);
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < linkedlist.size(); i++) {

						sb.append(linkedlist.get(i));
						sb.append("-");

					}
					pids = sb.substring(0, sb.length() - 1);

				}

			}

		}
		// 如果不存在直接添加
		Cookie cookie_pids = new Cookie("pids", pids);
		response.addCookie(cookie_pids);

		request.setAttribute("product", product);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBeanid", pageBeanid);
		request.setAttribute("name", name);
		request.getRequestDispatcher("/user/productInfo.jsp").forward(request,
				response);

	}	
	
	// 在线搜索仿百度提示搜索功能
	public void onlineSearchlikeBaidu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String word = request.getParameter("word");
		//System.out.println(word);
		List<Object> onlineSearchlikeBaidulist=null;
		//模糊查询，查询出用户可能需要查找的商品
		
		//情况一，如果输入的不是null
		if (word!=null&&!word.equals("")&&!word.trim().equals("")) {
			try {
				onlineSearchlikeBaidulist = productService.onlineSearchlikeBaidu(word);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//json数组显示返回回去
			Gson gson=new Gson();
			
			String json = gson.toJson(onlineSearchlikeBaidulist);
			//System.out.println(json);
			response.getWriter().write(json);
		}else{
			
			//情况2，如果输入是null
			
			response.getWriter().write("{\"name\":\"tom\"}");
			
			
		}
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
