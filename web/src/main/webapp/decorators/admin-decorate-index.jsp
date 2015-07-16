<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:title/></title>
    <style type="text/css">
        #ext{
            width: 100%;
            max-height: 1000px;
        }
        div{
            margin: 0px;
            padding-left: 0px;
            padding-right: 0px;
        }
        .menu{
            height:50px;line-height:50px;
        }

    </style>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryUI/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryUI/jquery-ui.structure.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqueryUI/jquery-ui.theme.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            scr="${pageContext.request.contextPath}/resources/jqueryUI/jquery-ui.min.js"></script>
    <sitemesh:head></sitemesh:head>
</head>
<body >
<div class="container-fluid " id="ext">
    <div class="row">
        <div class="col-xs-2 col-md-2 menu" id="left">
           <div id="userIno" class="row " align="left">userInfo</div>
           <div id="menu2" class="row " align="left">menu2</div>
        </div>
        <div class="col-xs-10 col-md-10" id="right">
            <div id="menu1" class="row">
                <nav class="navbar navbar-default">
                    <div class="navbar-header menu">
                    <div class="collapse navbar-collapse">
                        <ui class="nav navbar-nav">
                            <li>test1</li>
                            <li class="divider-vertical"></li>
                            <li >test2</li>
                            <li class="divider-vertical"></li>
                            <li>test3</li>
                            <li class="divider-vertical"></li>
                            <li>test1</li>
                            <li class="divider-vertical"></li>
                            <li>test1</li>
                        </ui>
                    </div>
                    </div>
                </nav>
            </div>
            <div id="main" class="row">main </div>
        </div>

    </div>

</div>

</body>
</html>
