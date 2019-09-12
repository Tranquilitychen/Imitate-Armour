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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/productList.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
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

		//在线搜索功能
		 

		 function onlineSearch(){
			 
			 //得到要搜索的内容
			 var searchValue=document.getElementById("nav_search").value;
			 //跳转到servlet业务
			 location.href="${pageContext.request.contextPath}/productServlet?method=onlineSearch&&pageBeanid=7&&name="+searchValue;
			 
			 
		 }

</script>
<style>
.allProduct .pageindex .first{
color:red;
}
.last{
color:blue;
}
</style>
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

<div class="allProduct">
    <div class="list">
    
    <c:forEach items="${pageBean.productList }"  var="pro">
    	<div class="product">
            <img src="${pageContext.request.contextPath}/${pro.pimg}" alt="">
            <div >${pro.pname }..${pro.pprice }</div>
            <div><a href="${pageContext.request.contextPath}/productServlet?method=productInfo&&pid=${pro.pid}&&currentPage=${pageBean.currentPage }&&pageBeanid=${pageBean.pageBeanid}&&name=${pageBean.pattern}">更多</a></div>

        </div>
    
    </c:forEach>
       

    </div>
    <div class="pageindex">
    
   <ul>
   
     <!-- 上一页 -->
   
           
            
            <c:if test="${pageBean.currentPage == 1}">
		 <li >   <a href="javascript:void(0);" class="last">上一页</a> </li>
		
	</c:if>
         <c:if test="${pageBean.currentPage != 1}">
		 <li >   <a href="${pageContext.request.contextPath}/productServlet?method=productList&&pageBeanid=${pageBean.pageBeanid}&&currentPage=${pageBean.currentPage -1}&&name=${pageBean.pattern}"  >上一页</a> </li>
		
	</c:if>
            
            
            <!-- index页码，如果是第一页那么第一页a连接每页作用，如果不是，那么a连接是跳转到servlet来得到分页的效果 -->
           <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
 
  <c:if test="${pageBean.currentPage == i}">
		 <li >  <a href="javascript:void(0);" class="first" >${i }</a></li>
		
	</c:if>
 
  <c:if test="${pageBean.currentPage != i}">
		 <li >  <a href="${pageContext.request.contextPath}/productServlet?method=productList&&pageBeanid=${pageBean.pageBeanid}&&currentPage=${i}&&name=${pageBean.pattern}" >${i }</a></li>
		
	</c:if>
    
    </c:forEach>
            
            
            
            
            
            
              <c:if test="${pageBean.currentPage == pageBean.totalPage}">
		 <li >   <a href="javascript:void(0);" class="last">下一页</a> </li>
		
	</c:if>
         <c:if test="${pageBean.currentPage !=pageBean.totalPage }">
		 <li >   <a href="${pageContext.request.contextPath}/productServlet?method=productList&&pageBeanid=${pageBean.pageBeanid}&&currentPage=${pageBean.currentPage +1}&&name=${pageBean.pattern}"  >下一页</a> </li>
		
	</c:if>
            
            <!-- 下一页 -->
            
          
            
            
            
            
            
            
        </ul>
        

        
    </div>
</div>

<div class="history">

<c:forEach items="${hostpro }" var="pro">
    <div class="product">
        <img src="${pageContext.request.contextPath}/${pro.pimg}" alt="">
        <div >${pro.pname }..${pro.pprice }</div>
       

    </div>
    
    </c:forEach>
    
</div>


</body>
</html>