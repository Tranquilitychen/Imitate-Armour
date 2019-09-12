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
	//index页面显示商品功能
	public void indexList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//查询所有product
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
		//李彤beanutils封装对象
		product product = new product();
		
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		//设置pid
		product.setPid(myUtils.getUUID());
		
		//存储到数据库
		try {
			adminService.addproduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminServlet?method=indexList");
		
		
	}

	// 删除页面的商品显示
	public void delproductpage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 查询所有product
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
	
	// 删除product
	public void delproduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pid = request.getParameter("pid");

		// 删除实现
		try {
			adminService.delproduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询所有product
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
	
	// 修改商品页面显示
	public void updateproductpage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 查询所有product
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
	
	
	//修改商品,商品信息回显
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
	
	//添加商品（img）
	public void addproductbyimg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		product product=new product();
		//创建集合由于存储对象的数据
		Map<String, Object> map=new HashMap<String , Object>();
		
		try {

			//1、创建磁盘文件项工厂
			
			
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			//2、创建文件上传的核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置上传文件的名称的编码
			upload.setHeaderEncoding("UTF-8");

			//ServletFileUpload的API
			boolean multipartContent = upload.isMultipartContent(request);//判断表单是否是文件上传的表单
			if(multipartContent){
				//是文件上传的表单
				//***解析request获得文件项集合
				List<FileItem> parseRequest = upload.parseRequest(request);
				if(parseRequest!=null){
					for(FileItem item : parseRequest){
						//判断是不是一个普通表单项
						boolean formField = item.isFormField();
						if(formField){
							//username=zhangsan
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");//对普通表单项的内容进行编码
							
							map.put(fieldName, fieldValue);
							
							//当表单是enctype="multipart/form-data"时 request.getParameter相关的方法
							//String parameter = request.getParameter("username");
							
						}else{
							//文件上传项
							//文件的名
							String fileName = item.getName();
							//System.out.println(fileName);
							//获得上传文件的内容
							InputStream in = item.getInputStream();
							String path_store = this.getServletContext().getRealPath("shangc");
							//会出现tomcat重新部署的情况
							OutputStream out = new FileOutputStream(path_store+"/"+fileName);
							
							IOUtils.copy(in, out);
							
							in.close();
							out.close();
						
							//删除临时文件
							item.delete();
							map.put("pimg", "shangc/"+fileName);
						}
					}
				}

			}else{
				//不是文件上传表单
				//使用原始的表单数据的获得方式 request.getParameter();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		try {
			//封装
			BeanUtils.populate(product, map);
			
			//设置uuid
			product.setPid(myUtils.getUUID());
			//存储
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
