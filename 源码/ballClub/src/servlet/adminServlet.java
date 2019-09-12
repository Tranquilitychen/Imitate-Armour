package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import domain.product;
import domain.type;
import service.adminServiceimpl;
import service.productServiceimpl;
import service.productServiceinter;
import utils.myUtils;

public class adminServlet extends baseServlet {
	
	adminServiceimpl adminService=new adminServiceimpl();
	productServiceinter productService=new productServiceimpl();
	//indexҳ����ʾ��Ʒ����
	public void indexList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ѯ����product
		List<product> adminProductlist=null;
		try {
			adminProductlist=adminService.indexList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("adminProductlist", adminProductlist);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		
	}

	//selectType
	public void selectType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<type> selectType =null;
		try {
		selectType = productService.indexType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(selectType);
		response.getWriter().write(json);
		
		
	}
	
	//addproduct
	public void addproduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String[]> map = request.getParameterMap();
		//��ͮbeanutils��װ����
		product product = new product();
		
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		//����pid
		product.setPid(myUtils.getUUID());
		
		//�洢�����ݿ�
		try {
			adminService.addproduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminServlet?method=indexList");
		
		
	}

	// ɾ��ҳ�����Ʒ��ʾ
	public void delproductpage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ѯ����product
		List<product> adminProductlist = null;
		try {
			adminProductlist = adminService.indexList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("adminProductlist", adminProductlist);
		request.getRequestDispatcher("/admin/delproduct.jsp").forward(request, response);
		
		
		
	}
	
	// ɾ��product
	public void delproduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pid = request.getParameter("pid");

		// ɾ��ʵ��
		try {
			adminService.delproduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ѯ����product
		List<product> adminProductlist = null;
		try {
			adminProductlist = adminService.indexList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("adminProductlist", adminProductlist);
		request.getRequestDispatcher("/admin/delproduct.jsp").forward(request,
				response);

	}
	
	// �޸���Ʒҳ����ʾ
	public void updateproductpage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ѯ����product
		List<product> adminProductlist = null;
		try {
			adminProductlist = adminService.indexList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("adminProductlist", adminProductlist);
		request.getRequestDispatcher("/admin/updateproduct.jsp").forward(request,
				response);

	}
	
	
	//�޸���Ʒ,��Ʒ��Ϣ����
	public void updateproduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String pid = request.getParameter("pid");
		product product=null;
		try {
			product = productService.productInfo(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("adminproduct", product);
		request.getRequestDispatcher("/admin/addproduct.jsp").forward(request,
				response);
	}
	
	//�����Ʒ��img��
	public void addproductbyimg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		product product=new product();
		//�����������ڴ洢���������
		Map<String, Object> map=new HashMap<String , Object>();
		
		try {

			//1�����������ļ����
			
			
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			//2�������ļ��ϴ��ĺ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			//�����ϴ��ļ������Ƶı���
			upload.setHeaderEncoding("UTF-8");

			//ServletFileUpload��API
			boolean multipartContent = upload.isMultipartContent(request);//�жϱ��Ƿ����ļ��ϴ��ı�
			if(multipartContent){
				//���ļ��ϴ��ı�
				//***����request����ļ����
				List<FileItem> parseRequest = upload.parseRequest(request);
				if(parseRequest!=null){
					for(FileItem item : parseRequest){
						//�ж��ǲ���һ����ͨ����
						boolean formField = item.isFormField();
						if(formField){
							//username=zhangsan
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");//����ͨ��������ݽ��б���
							
							map.put(fieldName, fieldValue);
							
							//������enctype="multipart/form-data"ʱ request.getParameter��صķ���
							//String parameter = request.getParameter("username");
							
						}else{
							//�ļ��ϴ���
							//�ļ�����
							String fileName = item.getName();
							//System.out.println(fileName);
							//����ϴ��ļ�������
							InputStream in = item.getInputStream();
							String path_store = this.getServletContext().getRealPath("shangc");
							//�����tomcat���²�������
							OutputStream out = new FileOutputStream(path_store+"/"+fileName);
							
							IOUtils.copy(in, out);
							
							in.close();
							out.close();
						
							//ɾ����ʱ�ļ�
							item.delete();
							map.put("pimg", "shangc/"+fileName);
						}
					}
				}

			}else{
				//�����ļ��ϴ���
				//ʹ��ԭʼ�ı����ݵĻ�÷�ʽ request.getParameter();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		try {
			//��װ
			BeanUtils.populate(product, map);
			
			//����uuid
			product.setPid(myUtils.getUUID());
			//�洢
			try {
				adminService.addproduct(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (IllegalAccessException | InvocationTargetException e) {


			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminServlet?method=indexList");
		
		
	}
	
	
	
	
	
}
