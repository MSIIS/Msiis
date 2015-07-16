<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style type="text/css">
        #ext {
            width: 100%;
            max-height: 1000px;
        }

        div {
            margin: 0px;
            padding-left: 0px;
            padding-right: 0px;
        }

        .menu {
            height: 50px;
            line-height: 50px;
        }

        .userinfo {
            height: 120px;
            line-height: 120px;
            background-color:#c1c1c1;
        }

        .menu2 {
            height: 750px;
            line-height: 750px;
            background-color: #d9dadc;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ILE后台管理</title>
    <script type="text/javascript">
        function turnMenu2(url) {
        }

    </script>
</head>
<body>
<div class="container-fluid " id="ext">
    <div class="row">
        <div class="col-xs-2 col-md-2 " id="left">
            <div id="userIno" class="row userinfo " align="left">
                <span style="font-size: 18">&nbsp;&nbsp;欢迎你，<strong>${user.userName}</strong>&nbsp;[${user.organization.orgName}]</span>
            </div>
            <div id="menu2" class="row menu2 ">
                <ul class="nav nav-pills nav-stacked">
                    <c:if test="${null!=menu2}">
                        <c:forEach items="${menu2}" var="menu" varStatus="m2">
                            <li role="presentation">
                                <div
                                        class="btn  btn-lg btn-block btn-default ">${menu.res_name}</div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
        <div class="col-xs-10 col-md-10" id="right">
            <div id="menu1" class="row ">
                <nav class="navbar navbar-default ">
                    <div class="navbar-header bg-info center-block">
                        <div class="collapse navbar-collapse">
                            <ui class="nav  nav-tabs btn-group-justified" role="group">
                                <c:forEach items="${navbar}" var="item" varStatus="st">
                                    <li role="presentation" class="menu">
                                        <div class="btn btn-lg " onclick="turnMenu2('${item.resourcePath}')"
                                             id="${item.id}">
                                            <b>${item.res_name}</b></div>
                                    </li>
                                    <li class=""></li>
                                </c:forEach>
                            </ui>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="main" class="row">
                SUCCESS
            </div>
        </div>
    </div>
</div>
</body>
</html>