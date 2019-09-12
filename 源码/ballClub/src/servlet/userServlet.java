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


//�û�����
public class userServlet extends baseServlet {

	userServiceinter userService = new userServiceimpl();

	//��¼����
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		HttpSession session = request.getSession();

		user user = new user();
		// ��¼��֤
		try {
			user = userService.login(username, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// �жϵ�¼�ǲ��ǳɹ�
		if (user != null) {
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("loginError", "�˺Ż������벻��");
			request.getRequestDispatcher("/user/login.jsp").forward(request,
					response);
		}

	}

	// ��֤�û�ע����֤���Ƿ���ȷ
	public void checkimage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ����
		HttpSession session = request.getSession();
		String word = request.getParameter("word");

		// ��֤��֤���׼ȷ��

		String real_word = (String) session.getAttribute("checkimage");

		boolean is_real = real_word.equals(word);
		// ���ص���json��ʽ���ַ���
		String json = "{\"isExist\":" + is_real + "}";

		response.getWriter().write(json);

	}
	
	// �û�ע��У���û����Ƿ���ڹ���
	public void checkloginname(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ����
		String name = request.getParameter("name");

		// Ĭ�ϲ�����false
		boolean isExistloginName = false;

		// У���Ƿ����
		try {
			isExistloginName = userService.isExistloginName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���ص���json��ʽ���ַ���
		String json = "{\"isExist\":" + isExistloginName + "}";

		response.getWriter().write(json);

	}
		
		
	//�û�ע�Ṧ��
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//����beanutils��װ��������
		Map<String, String[]> user_map = request.getParameterMap();
		
		user user=new user();
		try {
			BeanUtils.populate(user, user_map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		//��û�в����������ݲ���
		user.setUid(myUtils.getUUID());
		//Ĭ���˺�δ����
		user.setState(0);
		//���ü�����
		user.setActive(myUtils.getUUID());

		try {
			userService.regist(user);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//����ע�ἤ���ʼ�����Ϣ
		String active=user.getActive();
		
		String email_message="��ϲ��ע��ɹ���������������ӽ��м����˻�"
				+ "<a href='http://localhost:8080/ballClub/userServlet?method=active&&active="+active+"'>"
						+ "��������˺�</a>";
		
		try {
			//����������ʼ�
			MailUtils.sendMail(user.getEmail(), email_message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�ض���һ�����������ҳ��
		response.sendRedirect(request.getContextPath()+"/user/registerSuccess.jsp");
		
	}
	
	
	//�û������˺Ź���
	public void active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//��ȡ����
		String active = request.getParameter("active");
		//�����ݿ��а��û��˺ŵ�state״̬��0��1
		
		try {
			userService.active(active);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�ض�����ҳ
		response.sendRedirect(request.getContextPath());
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
