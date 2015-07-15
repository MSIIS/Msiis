<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<head>
    <title>I乐平台</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/view/js/index-main.js"></script>
</head>
<body background="#fff">
<nav class="navbar navbar-inverse" role="navigation ">
    <div class="container-fluid" id="index-top">
        <div class="col-md-12">
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:void(0);"
                        >平台后台管理</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="javascript:void(0);">系统配置</a></li>
                    <li><a href="javascript:void(0);" onclick="userManage();">用户管理</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">娱乐专区1 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:void(0);" onclick="index_choose();">双色球</a></li>
                            <li><a href="#" onclick="ssq();">双色球杀号</a></li>
                            <li><a href="#">双色球开奖</a></li>
                            <li><a href="javascript:void(0);" onclick="dlt();">大乐透</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
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
