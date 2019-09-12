<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin_common_css.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/addproduct.css">
<script>

//selectType
 window.onload=function(){
	 
	 var content="";
	 $.ajax({
			async:true,
			url:"${pageContext.request.contextPath }/adminServlet?method=selectType",
			
			type:"POST",
			dataType:"json",
			success:function(data){
				
				for(var i=0;i<data.length;i++){
					
					content+="<option value="+data[i].type_id+">"+data[i].type_name+"</option>";
				}
				$("#select").html(content);
			}
		});
	 
	 
	 //选定option
	 var cid="${adminproduct.type_id}";
	 var options = document.getElementById("select").getElementsByTagName("option");
	 for(var i=0;i<options.length;i++){
		 
		 if(cid==options[i].value){
			 options[i].selected=true;
		 }
	 }
 }


</script>
<body>

<div class="admin_nav clearfix">
    <div class="type"><a href="${pageContext.request.contextPath}/admin/addproduct.jsp">添加商品</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/admin/addproductbyimg.jsp">添加商品（图片）</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/adminServlet?method=delproductpage">删除</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/adminServlet?method=updateproductpage">修改商品</a></div>
    <div class="type"><a href="${pageContext.request.contextPath }/index.jsp">首页</a></div>
</div>

<form action="${pageContext.request.contextPath }/adminServlet?method=addproduct" method="post">
    商品名称：<input type="text" name="pname" value="${adminproduct.pname }"><br>
    商品价格：<input type="text" name="pprice" value="${adminproduct.pprice }"><br>
    商品描述：<input type="text" name="ppdesc" value="${adminproduct.pdesc }"><br>
    
    商品分类：<select name="type_id" id="select">
                
                
                
             </select><br>
    <input type="submit" value="提交">
</form>


</body>
</html>