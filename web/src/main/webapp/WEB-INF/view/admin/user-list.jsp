<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<%
    String contextPath = request.getContextPath();
%>
<head>
    <title>用户管理页</title>
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
             <c:forEach items="${user.userRoleOrgRelationList}" var="relation" varStatus="relatst"  >
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
