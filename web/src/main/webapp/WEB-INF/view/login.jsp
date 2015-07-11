<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="<%=contextPath%>/resources/bootstrap/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=contextPath%>/resources/bootstrap/css/bootstrap-theme.min.css">
</head>
<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/util/com-submit.js"></script>	
<body>
	<div class="container"  id="main">
		<div class="row" align="center">
			<div class="col-md-4 col-md-offset-4" >
			  <form action="" id="login" method="post" >
				<table class="table table-bordered"  style="background-color:#F5F5F5" >
					<tr>
						<td align="center" style="border:none"><span>用户名</span></td>
						<td style="border:none">
							<div class="input-group">
								<input type="text" name="name" class="form-control" placeholder="Username" id="name">
							</div>
						</td>
					</tr>

					<tr>
						<td align="center" style="border:none">密码</td>
						<td style="border:none">
						<div class="input-group">
								<input type="password"  name="password" class="form-control" placeholder="Password" id="password">
							</div>
						</td>
					</tr>
					<tr >
						<td align="center" colspan="2" style="border:none"><div>
						<button type="submit" class="btn btn-primary" id ="sumbit" onclick="submit1()">确定</button>
						&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;
						<button type="reset" class="btn btn-default" id="cancle" onclick="cancle()"> 取消</button>
						</div></td>
					</tr>
				</table>
				</form>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
 var contextPath="<%=contextPath%>";
   $(function(){
	   $("#main").css({
		    "position": "absolute",
		    "top": $(document).height()/5,
		    "left": $(document).width()/3,
		}); 
   });
   
   function submit1(){
	   var url =contextPath+"/admin/login/submit";
	   $("#login").attr('action',url);
	   $("#login").submit();
   }
   
</script>
</html>