<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<head>
    <%
        String contextPath = request.getContextPath();
    %>
    <link rel="stylesheet"
          href="<%=contextPath%>/resources/bootstrap/css/bootstrap.min.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet"
          href="<%=contextPath%>/resources/bootstrap/css/bootstrap-theme.min.css">
    <style type="text/css">
        .color1 {background:#044}
        .left {width:100%; height:100%;}
    </style>
</head>
<script type="text/javascript"
        src="<%=contextPath%>/resources/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="<%=contextPath%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var contextPath="<%=contextPath%>";
</script>
<script type="text/javascript"
        src="<%=contextPath%>/resources/foundation/js/index-main.js"></script>
<body background="#fff">
<nav class="navbar navbar-inverse"  role="navigation ">
<div class="container-fluid" id="index-top">
    <div class="row">
        <div class="col-md-12">
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:void(0);"
                   onclick="zyzl();">自娱自乐</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="javascript:void(0);" onclick="index_choose();">双色球</a></li>
                    <li><a href="javascript:void(0);" onclick="dlt();">大乐透</a></li>
                    <li class="dropdown"><a href="javascript:void(0);"
                                            class="dropdown-toggle" data-toggle="dropdown"> 其他 <b
                            class="caret"></b>
                    </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Test1</a></li>
                            <li><a href="#">Test2</a></li>
                            <li><a href="#">Test3 Report</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Test4</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Test5</a></li>
                        </ul></li>
                </ul>
            </div>
        </div>
    </div>

</div>
</nav>
<div class="container-fluid ">
    <div class="row-fluid  ">
        <div class="col-md-2 " id="left">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="#" onclick="index_choose();">双色球精选</a></li>
                <li role="presentation"><a href="#" onclick="ssq();">双色球杀号</a></li>
                <li role="presentation"><a href="#">双色球开奖</a></li>
            </ul>
        </div>
        <div class="col-md-10" id="index-main"></div>

    </div>
</div>
</body>
</html>
