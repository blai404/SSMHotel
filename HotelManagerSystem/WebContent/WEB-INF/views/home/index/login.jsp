<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Author" content="BLAI">
<meta name="Keywords" content="BLAI大酒店">
<meta name="Description" content="BLAI大酒店">
<title>BLAI大酒店登录页面</title>
<link href="../resources/home/css/index.css" type="text/css" rel="Stylesheet" />
<link href="../resources/home/css/login.css" type="text/css" rel="Stylesheet" />
</head>
<body>
	<header>
		<div>
			<a href="index.html"><img src="../resources/home/images/logo-1.jpg" alt=""></a> <span>会员登录</span>
		</div>
	</header>
	<section>
		<div class="left">
			<img src="../resources/home/images\index.jpg" alt="">
		</div>
		<div class="login">
			<div id="normal">
				<ul id="nor_log">
					<li class="name" style="margin-top: 25px;">
						<input id="name" name="uname" type="text" placeholder="请输入用户名"> <span class="icon"></span>
					</li>
					<li class="pwd" style="margin-top: 25px;">
						<input id="password" name="upwd" type="password" placeholder="密码"> <span class="icon"></span>
					</li>
				</ul>
				<div class="codes" style="margin-top: 25px;">
					<input id="vcode" type="text" class="blur" placeholder="请输入验证码" /> 
					<img id="cpacha-image" maxlength="4" src="../system/get_cpacha?vl=4&w=173&h=33&type=customerLoginCpacha" onclick="changeCpacha()" style="cursor: pointer;" class="code" />
				</div>
			</div>
			<div class="log" id="bt_login" style="margin-top: 25px; cursor: pointer;">登 录</div>
		</div>
		<div class="reg">
			<a href="reg">立即注册 &gt;&gt;</a>
		</div>
	</section>
	<footer class="clear">
		<%@include file="../common/footer.jsp"%>
	</footer>
	<script src="../resources/home/js/jquery-1.11.3.js"></script>
</body>
<script type="text/javascript">
	function changeCpacha(){
		$("#cpacha-image").attr("src","../system/get_cpacha?vl=4&w=173&h=33&type=customerLoginCpacha" + new Date().getTime())
	}
	
	$(document).ready(function(){
		$("#bt_login").click(function(){
			var name = $("#name").val();
			var password = $("#password").val();
			var vcode = $("#vcode").val();
			if(name == '' || name == 'undefined'){
				alert("请填写用户名！");
				return;
			}
			if(password == '' || password == 'undefined'){
				alert("请填写密码！");
				return;
			}
			if(vcode == '' || vcode == 'undefined'){
				alert("请填写验证码！");
				return;
			}
			$.ajax({
				url:'login',
				type:'post',
				dataType:'json',
				data:{name:name,password:password,vcode:vcode},
				success:function(data){
					if(data.type == 'success'){
						window.location.href = 'index';
					}else{
						alert(data.msg);
						changeCpacha();
					}
				}
			});
		})
	});
</script>
</html>
