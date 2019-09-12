package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.user;
import service.userServiceimpl;
import service.userServiceinter;
import utils.MailUtils;
import utils.myUtils;


//用户功能
public class userServlet extends baseServlet {

	userServiceinter userService = new userServiceimpl();

	//登录功能
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		HttpSession session = request.getSession();

		user user = new user();
		// 登录验证
		try {
			user = userService.login(username, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// 判断登录是不是成功
		if (user != null) {
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("loginError", "账号或者密码不对");
			request.getRequestDispatcher("/user/login.jsp").forward(request,
					response);
		}

	}

	// 验证用户注册验证码是否正确
	public void checkimage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取参数
		HttpSession session = request.getSession();
		String word = request.getParameter("word");

		// 验证验证码的准确性

		String real_word = (String) session.getAttribute("checkimage");

		boolean is_real = real_word.equals(word);
		// 返回的是json格式的字符串
		String json = "{\"isExist\":" + is_real + "}";

		response.getWriter().write(json);

	}
	
	// 用户注册校验用户名是否存在功能
	public void checkloginname(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取参数
		String name = request.getParameter("name");

		// 默认参数是false
		boolean isExistloginName = false;

		// 校验是否存在
		try {
			isExistloginName = userService.isExistloginName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回的是json格式的字符串
		String json = "{\"isExist\":" + isExistloginName + "}";

		response.getWriter().write(json);

	}
		
		
	//用户注册功能
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//利用beanutils封装对象数据
		Map<String, String[]> user_map = request.getParameterMap();
		
		user user=new user();
		try {
			BeanUtils.populate(user, user_map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		//把没有插入对象的数据插入
		user.setUid(myUtils.getUUID());
		//默认账号未激活
		user.setState(0);
		//设置激活码
		user.setActive(myUtils.getUUID());

		try {
			userService.regist(user);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//设置注册激活邮件的信息
		String active=user.getActive();
		
		String email_message="恭喜您注册成功，请点击下面的连接进行激活账户"
				+ "<a href='http://localhost:8080/ballClub/userServlet?method=active&&active="+active+"'>"
						+ "点击激活账号</a>";
		
		try {
			//发生激活的邮件
			MailUtils.sendMail(user.getEmail(), email_message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//重定向到一个激活邮箱的页面
		response.sendRedirect(request.getContextPath()+"/user/registerSuccess.jsp");
		
	}
	
	
	//用户激活账号功能
	public void active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//获取参数
		String active = request.getParameter("active");
		//到数据库中把用户账号的state状态从0变1
		
		try {
			userService.active(active);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//重定向到主页
		response.sendRedirect(request.getContextPath());
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
