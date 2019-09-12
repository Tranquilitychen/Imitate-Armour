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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/updateproduct.css">
<body>

<div class="admin_nav clearfix">
   <div class="type"><a href="${pageContext.request.contextPath}/admin/addproduct.jsp">添加商品</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/admin/addproductbyimg.jsp">添加商品（图片）</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/adminServlet?method=delproductpage">删除</a></div>
    <div class="type"><a href="${pageContext.request.contextPath}/adminServlet?method=updateproductpage">修改商品</a></div>
    <div class="type"><a href="${pageContext.request.contextPath }/index.jsp">首页</a></div>
</div>

<div class="admin_prolist">
<c:forEach items="${adminProductlist }" var="pro">

    <div class="admin_pro clearfix">
        <div class="admin_pro_img fl"><img src="${pageContext.request.contextPath}/${pro.pimg}" alt=""></div>
        <div class="admin_price fl">${pro.pprice }</div>
        <div class="admin_others fl">${pro.pname }</div>
 		<div class="update fr"><a href="${pageContext.request.contextPath}/adminServlet?method=updateproduct&&pid=${pro.pid}">修改</a></div>
    </div>
    
</c:forEach>
</div>
</body>
</html>