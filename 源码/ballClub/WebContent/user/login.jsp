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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common_index.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css">
<body>

<div class="all">
    <form action="${pageContext.request.contextPath }/userServlet?method=login" method="post">
        账号:<input type="text" name="username"><br>
        密码:<input type="password" name="password"><br>
        登录:<input type="submit" value="登录" style="cursor: pointer">
    </form>
    <div class="error" >
    	
				${loginError }
		
		
    </div>
    <div class="regist"><a href="regist.jsp">没有账号？注册一个</a></div>
</div>








</body>
</html>