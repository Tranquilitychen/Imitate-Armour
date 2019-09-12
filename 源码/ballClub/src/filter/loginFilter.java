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

	// ��֤��¼��filter
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		//��ǿhttp����session
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		//��ȡ���ʵĵ�ַ
		StringBuffer path = request.getRequestURL();
		//http://localhost:8080/ballClub/
//		System.out.println(path);
		
		//����Ĭ����ҳ
		if (path.toString().equals("http://localhost:8080/ballClub/")) {
			
			chain.doFilter(request, response);
			return ;
		}
		//������ҳ
		if (path.indexOf("index.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//���ʵ�¼
		if (path.indexOf("login.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//����ע��
		if (path.indexOf("regist.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//��¼��������ҳ�治�ܱ�����
		if (path.indexOf("registerSuccess.jsp")>-1) {
			chain.doFilter(request, response);
			return ;
		}
		//��¼��֤
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
