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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/orders.css">
    <script>



		window.onload=function(){
			
			var indexType="";
			  //页面商品分类的查询
			  $.ajax({
					async:true,
					url:"${pageContext.request.contextPath }/productServlet?method=indexType",
					
					type:"POST",
					dataType:"json",
					success:function(data){
						
						for(var i=0;i<data.length;i++){
							//点击跳转到对应的productList页面
							indexType+=" <li><a href='${pageContext.request.contextPath}/productServlet?method=productList&&pageBeanid="+data[i].type_id+"'>"             
							+data[i].type_name+"</a></li>";
							
						}
						$("#indexType").html(indexType);
					}
				});
			
		}

		//购买订单
		function buy(oid){
			
			location.href="${pageContext.request.contextPath}/orderServlet?method=buyOrderinMyorders&&oid="+oid;
			
		}
		//删除订单
		function del(oid){
			
			location.href="${pageContext.request.contextPath}/orderServlet?method=delOrder&&oid="+oid;
			
		}
		//删除所有订单
		function delAll(){
			
			location.href="${pageContext.request.contextPath}/orderServlet?method=delAll";
			
		}
		//在线搜索功能
		 

		 function onlineSearch(){
			 
			 //得到要搜索的内容
			 var searchValue=document.getElementById("nav_search").value;
			 //跳转到servlet业务
			 location.href="${pageContext.request.contextPath}/productServlet?method=onlineSearch&&pageBeanid=7&&name="+searchValue;
			 
			 
		 }
</script>
    
<body>
<!--头部-->
<div class="header">
    <div class="header_left fl">
        <div class="header_font">满199元包邮</div>
    </div>
    <div class="header_right fr">
        <ul class="header_ul">
            <li>123456789</li>
            <li><a href="${pageContext.request.contextPath}/user/onlineSearch.jsp">在线搜索</a></li>
            <li><a href="#">线下门店</a></li>
        </ul>
    </div>
</div>
<!--导航-->
<div class="nav clearfix">
    <div class="index fl"><a href="#"><img src="${pageContext.request.contextPath}/img/1.jpg" alt=""></a></div>
    <ul class="nav_ul fl clearfix" id="indexType">
       
       
       
    </ul>
    <div class="nav_right fr clearfix">
        <div class="nav_search fl">
            <input type="text" id="nav_search">
            <span class="nav_search_img" onclick="onlineSearch()"><a href="#"></a></span>
        </div>
        <div class="nav_login_regist fl">
             <c:if test="${empty user}">
		<span class="nav_login"><a href="user/login.jsp">登录</a></span> |
            <span class="nav_regist"><a href="${pageContext.request.contextPath}/user/regist.jsp">注册</a></span>
		
		</c:if>
        <c:if test="${!empty user}">
        <span class="nav_regist"><a href="javascript:void(0);">${user.name }</a></span>|
		 <span class="nav_regist"><a href="${pageContext.request.contextPath}/orderServlet?method=myOrder">订单</a></span>
		 
		</c:if>   
        </div>
        <div class="nav_cart fl"><a href="${pageContext.request.contextPath}/user/cart.jsp">购物车</a></div>
    </div>
</div>

<div class="all">


<c:forEach items="${myOrders }" var="order">
    <div class="order">

	<c:forEach items="${order.orderItems }" var="orderitem">
        <div class="orderitem">
        
            <div class="orderid">${order.oid }...
            	<c:if test="${order.state==1 }">已购买</c:if>
            	<c:if test="${order.state==0 }">未购买</c:if>
            </div>
            
            <div class="product">
                <div class="img"><img src="${pageContext.request.contextPath}/${orderitem.product.pimg}" alt=""></div>
                <div class="other">价格：${orderitem.product.pprice}..${orderitem.total_price}</div>
                <div class="other">数量：${orderitem.count}</div>
            </div>
            
        </div>
      </c:forEach>
        <div class="price">总价：${order.total }</div>
        <button onclick="buy('${order.oid}')">立即购买</button>
        <button onclick="del('${order.oid}')">删除订单</button>
        
        
        
    </div>

  </c:forEach>

	<c:if test="${!empty myOrders }"><button onclick="delAll()">清空订单</button></c:if>
    
</div>


</body>
</html>