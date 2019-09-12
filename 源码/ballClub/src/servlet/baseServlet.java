package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class baseServlet extends HttpServlet{

	
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
	
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");
		//利用反射，调用方法
		try {
			String method=request.getParameter("method");
			Class clazz=this.getClass();
			Method themethod = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			themethod.invoke(this, request,response);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
	}
	
	
}
