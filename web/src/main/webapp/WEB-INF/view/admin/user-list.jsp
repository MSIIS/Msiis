<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<%
    String contextPath = request.getContextPath();
%>
<head>
    <title>用户管理页</title>
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
</head>
<body>
<table class="table table-hove">
     <tr class="success">
        <td>id</td>
         <td>name</td>
         <td>role</td>
         <td>操作</td>
     </tr>
   <c:forEach items="${users.data}" var="user" varStatus="userSt">
     <tr class="info">
       <td>${user.id}</td>
        <td>${user.userName}</td>
         <td>
             <c:forEach items="${user.userRoleRelationList}" var="relation" varStatus="relatst"  >
                 <div>${relation.role.name}</div>
             </c:forEach>

         </td>
       <td>
           <%--<a href="${pageContext.request.contextPath}/person/toupdate.action?id=${p.id}">修改</a>--%>
       </td>
     </tr>
     </c:forEach>
  </table>
</body>
</html>
