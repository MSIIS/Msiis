<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:title/></title>

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
<body>
<sitemesh:body></sitemesh:body>
</body>
</html>
