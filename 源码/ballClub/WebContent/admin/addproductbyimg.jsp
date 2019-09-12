<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin_common_css.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/addproductbyimg.css">
<script>
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

<form action="${pageContext.request.contextPath }/adminServlet?method=addproductbyimg" method="post" enctype="multipart/form-data">
    商品名称：<input type="text" name="pname"><br>
    商品价格：<input type="text" name="pprice"><br>
    商品描述：<input type="text" name="pdesc"><br>
    商
    商品分类：<select name="type_id" id="select">
    
    
</select><br>
    商品图片:<input type="file" name="filename"><br>
    <input type="submit" value="提交">
</form>



</body>
</html>