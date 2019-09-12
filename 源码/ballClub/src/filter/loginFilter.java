package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.user;


public class loginFilter implements Filter {

    public loginFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// 验证登录的filter
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		//增强http，获session
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		//获取访问的地址
		StringBuffer path = request.getRequestURL();
		//http://localhost:8080/ballClub/
//		System.out.println(path);
		
		//访问默认主页
		if (path.toString().equals("http://localhost:8080/ballClub/")) {
			
			chain.doFilter(request, response);
			return ;
		}
		//访问主页
		if (path.indexOf("index.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//访问登录
		if (path.indexOf("login.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//访问注册
		if (path.indexOf("regist.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//登录激活邮箱页面不能被过滤
		if (path.indexOf("registerSuccess.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//登录验证
		user user = (user) session.getAttribute("user");
		
		if (user!=null) {
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
		}
		
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
