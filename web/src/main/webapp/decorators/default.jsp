<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<%
    String contextPath = request.getContextPath();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:title /> 111111111111</title>
    <link rel="stylesheet"
          href="<%=contextPath%>/resources/bootstrap/css/bootstrap.min.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet"
          href="<%=contextPath%>/resources/bootstrap/css/bootstrap-theme.min.css">
    <style type="text/css">
        .color1 {background:#044}
        .left {width:100%; height:100%;}
    </style>
    <script type="text/javascript">
        var contextPath="<%=contextPath%>";
    </script>
    <script type="text/javascript"
            src="<%=contextPath%>/resources/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="<%=contextPath%>/resources/bootstrap/js/bootstrap.min.js"></script>
    <sitemesh:head />
</head>
<body>
<sitemesh:body ></sitemesh:body>
</body>
</html>
