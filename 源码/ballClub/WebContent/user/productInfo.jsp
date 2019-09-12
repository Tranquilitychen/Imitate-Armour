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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/productInfo.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<body>

<div class="all">

    <div class="img">
        <img src="${pageContext.request.contextPath }/${product.pimg }" alt="">
    </div>
    <div class="name ord">${product.pname }</div>
    <div class="price ord">${product.pprice}</div>
    <div class="other ord">${product.pdesc }</div>
    <form action="${pageContext.request.contextPath }/cartServlet?method=addCart&&pid=${product.pid}" method="post">
        数量;<input type="text" name="count" value="1"><br>
        <input type="submit" value="加入购物车">
    </form>
    <button class="ord" onclick="back()">返回</button>
</div>
<script>
   
function back(){
	
	location.href="${pageContext.request.contextPath }/productServlet?method=productList&&currentPage=${currentPage }&&pageBeanid=${pageBeanid}&&name=${name}";
	
	
}

</script>




</body>
</html>