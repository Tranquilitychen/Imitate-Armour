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
	
	//��ҳtype��ʾ
	public void indexType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ѯtype
		List<type> list =null;
		try {
			 list = productService.indexType();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//������ajax������ֵ��һ��json����
		
		Gson Gson=new Gson();
		String json = Gson.toJson(list);
		//System.out.println(json);
		//����ֵ
		response.getWriter().write(json);
		
		
		
	}

	// productListҳ��Ĺ�����ʾ
	public void productList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//���Դ���
		//System.out.println(request.getParameter("pageBeanid"));
		
		//��ȡ����
		String pageBeanid = request.getParameter("pageBeanid");
		String currentPage_String = request.getParameter("currentPage");
		
		//��������,Ϊ�˱���new String���ֿ�ָ������������
		String pattern = request.getParameter("name");
		if (pattern!=null) {
			 pattern=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		}else{
			pattern="";
		}

		
		pageBean pB=new pageBean();
		//�õ�һ��pageBean����
		try {
			pB=productService.productList(pageBeanid, currentPage_String,pattern);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//���Դ���
		//System.out.println(pB.getProductList());
		//���䵽ҳ����
		request.setAttribute("pageBean", pB);
		
		//�����ʷ��¼����
		List<product> hostpro=new ArrayList<product>();
		Cookie[] cookies=request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					String pids=cookie.getValue();
					//�и�pids
					String [] split=pids.split("-");
					//�ֱ������ѯproduct
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

	//������������
	public void onlineSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//Ҳ�Ƿ�ҳ���ܣ�����productlist����
		productList(request, response);
		
		
		

	}

	// productInfo����,������Ʒ��Ϣ��ʾ
	public void productInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		String pageBeanid = request.getParameter("pageBeanid");

		// Ϊ�˱���new String���ֿ�ָ������������
		String name = request.getParameter("name");
		if (name != null) {
			name = new String(request.getParameter("name").getBytes(
					"ISO-8859-1"), "UTF-8");
		} else {
			name = "";
		}

		product product = new product();

		// ������Ʒ
		try {
			product = productService.productInfo(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ʷ��¼����
		// 123-13-13123-12312����ʾ�洢��cookie���棬��ʵ���������
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		// ���ԭ�����Ѿ����ڣ�Ҫ���мӹ�����
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("pids")) {

					pids = cookie.getValue();
					// �ָ�
					String[] split = pids.split("-");
					// ת��Ϊlist����
					List<String> list = Arrays.asList(split);
					// ת��Ϊlinked�������
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
		// ���������ֱ�����
		Cookie cookie_pids = new Cookie("pids", pids);
		response.addCookie(cookie_pids);

		request.setAttribute("product", product);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBeanid", pageBeanid);
		request.setAttribute("name", name);
		request.getRequestDispatcher("/user/productInfo.jsp").forward(request,
				response);

	}	
	
	// ���������°ٶ���ʾ��������
	public void onlineSearchlikeBaidu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String word = request.getParameter("word");
		//System.out.println(word);
		List<Object> onlineSearchlikeBaidulist=null;
		//ģ����ѯ����ѯ���û�������Ҫ���ҵ���Ʒ
		
		//���һ���������Ĳ���null
		if (word!=null&&!word.equals("")&&!word.trim().equals("")) {
			try {
				onlineSearchlikeBaidulist = productService.onlineSearchlikeBaidu(word);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//json������ʾ���ػ�ȥ
			Gson gson=new Gson();
			
			String json = gson.toJson(onlineSearchlikeBaidulist);
			//System.out.println(json);
			response.getWriter().write(json);
		}else{
			
			//���2�����������null
			
			response.getWriter().write("{\"name\":\"tom\"}");
			
			
		}
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
