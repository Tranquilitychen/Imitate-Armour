<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common_index.css" type="text/css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/regist.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js" type="text/javascript"></script>
<script>
<!--变换验证码 -->
    function change(obj){
		obj.src="${pageContext.request.contextPath }/checkedimage?time="+new Date().getTime();
    }
    
   //自定义校验规则:验证注册名是否存在
   $.validator.addMethod(
		//规则的名称
		"checkname",
		//校验的函数
		function(value,element,params){
			
			//定义一个标志
			var flag = false;
			//alert(1);
			//value:输入的内容
			//element:被校验的元素对象
			//params：规则对应的参数值
			//目的：对输入的username进行ajax校验
			$.ajax({
				async:false,
				url:"${pageContext.request.contextPath }/userServlet?method=checkloginname",
				data:{"name":value},
				type:"POST",
				dataType:"json",
				success:function(data){
					//alert(1);
					flag = data.isExist;
				}
			});
			
			
			//返回false代表该校验器不通过
			return !flag;
		}
		
	);
   
 //自定义校验规则:验证 验证码是否正确
   $.validator.addMethod(
		//规则的名称
		"checkimage",
		//校验的函数
		function(value,element,params){
			
			
			var flag = true;
			
			$.ajax({
				async:false,
				url:"${pageContext.request.contextPath }/userServlet?method=checkimage",
				data:{"word":value},
				type:"POST",
				dataType:"json",
				success:function(data){
					//alert(1);
					flag = data.isExist;
				}
			});
			
			
			//返回false代表该校验器不通过
			return flag;
		}
		
	);
    <!--验证form -->

	 $(function(){
		 
		 $("#regist_form").validate({
			 
			 rules:{
				 name:{
					 required:true,
					 checkname:true
				 },
				 password:{
					 required:true,
					 rangelength:[4,8]
				 },
				 same_password:{
					 required:true,
					 equalTo:"[name='password']"
				 },
				 sex:{
					 required:true
				 },
				 email:{
					 required:true,
					 email:true
				 },
				 checkimage:{
					 required:true,
					 checkimage:true
				 }
				 
			 },
			 messages:{
				 
				 name:{
					 required:"用户名不能为空",
					 checkname:"用户名已存在"
				 },
				 password:{
					 required:"密码不能为空",
					 rangelength:"密码必须在4到8位"
				 },
				 same_password:{
					 required:"确认密码不能为空",
					 equalTo:"两次密码不一致"
				 },
				 sex:{
					 required:"性别不能为空"
				 },
				 email:{
					 required:"邮箱不能为空",
					 email:"邮箱格式不对"
				 },
				 checkimage:{
					 required:"验证码不能为空",
					checkimage:"验证码不正确"
				 }
				 
			 }
			 
			 
			 
			 
		 });
		 
		 
		 
		 
	 });
	 
 

</script>
<body>

<form action="${pageContext.request.contextPath }/userServlet?method=regist" method="post" id="regist_form">

    账号：<input type="text" name="name"><br>
<label for="name" class="error"></label><br>

    密码：<input type="password" name="password"><br>
<label for="password" class="error"></label><br>
    确认密码：<input type="password" name="same_password"><br>
  <label for="same_password" class="error"></label><br>
    性别：男：<input type="radio" name="sex" value="1"><br>
    女：<input type="radio" name="sex" value="0"><br>
    <label for="sex" class="error"></label><br>
    邮箱：<input type="email" id="email" name="email"><br>
<label for="email" class="error"></label><br>
    地址：<input type="address" name="address"><br>

    电话：<input type="text" name="telephone"><br>
    验证码： <input type="text" name="checkimage"> <img src="${pageContext.request.contextPath }/checkedimage" onclick="change(this)"><br>
  <label for="checkimage" class="error"></label><br>
    注册：<input type="submit" >

</form>

</body>
</html>