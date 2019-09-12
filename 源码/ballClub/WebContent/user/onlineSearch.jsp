<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/onlineSearch.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<body>
<script>
  //仿百度提示框ajax搜索功能
  $(function(){
	  
	 
	  
	  $("#searchValue").keyup(function(){
		  
		  		var value=this.value;
			  var content="";
			  
			  
			  
			  $.ajax({
					async:true,
					url:"${pageContext.request.contextPath }/productServlet?method=onlineSearchlikeBaidu",
					data:{"word":value},
					type:"POST",
					dataType:"json",
					success:function(data){
						
						if(data.name=="tom"){
							//用户输入是null的情况
							
						}else{
							//用户输入不是null的情况
							//alert(1);
							for(var i=0;i<data.length;i++){
								
								content+="<div  onclick='baidu(this)'>"+data[i]+"</div>";
								
							}
							
						}
						

						 $("#news").html(content);
						 $("#news").css("display","block");
						
					}
				});
			  
			  
		  
		  
	  });
	  
	 
	  
	  
	  
  });
  
  //仿百度搜索选择关键字功能
  //只可以选择这种方法的选择关键字，如果是其他方法，可能存在blur和onclick事件的优先级问题
  function baidu(obj){
	  
	  var a = $(obj).html();
	 
	  $("#searchValue").val(a);
	  $("#news").css("display","none");
  }
  
  
  

</script>


<div class="all">
    <div class="search"><input type="text" id="searchValue"><span id="onlineSearch" onclick="onlineSearch()"></span></div>
    <div class="news" id="news" style="display:none">
        
      
    </div>
</div>

<script>
 
 //在线搜索功能
 

 function onlineSearch(){
	 
	 //得到要搜索的内容
	 var searchValue=document.getElementById("searchValue").value;
	 //跳转到servlet业务
	 location.href="${pageContext.request.contextPath}/productServlet?method=onlineSearch&&pageBeanid=7&&name="+searchValue;
	 
	 
 }
  
 
 
  
  

</script>


</body>
</html>

















