<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户注册</title>
    <script type="text/javascript">

        $(function(){
            $("#register").ajaxForm(function(data){
                if(data.success){
                    alert("注册成功");
                }else{
                    alert(data.message);
                }
            });
        });

    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/user/register/submit" method="post" class="form-horizontal" id="register">
    <div class="col-sm-4">
        <label for="username">loginName:</label><input name="userName" type="text" id="username" class="form-control col-sm-2"/><br/>
        <label for="password">password:</label> <input name="password" type="password" id="password" class="form-control col-sm-2"/><br/>
        <label for="nickName">nickName:</label> <input name="nickName" type="text" id="nickName" class="form-control col-sm-2"/><br/>
        <label for="realName">realName:</label> <input name="realName" type="text" id="realName" class="form-control col-sm-2"/><br/>
        <label for="salt">random: </label><input id="salt" name="salt" type="text" class="form-control col-sm-2"/><br/>
        <label for="orgId">orgId</label>
        <select name="orgId" id="orgId"   class="form-control">
            <c:forEach items="${selectOrgs}" var="org" varStatus="orgId">
                <option value="${org.id}">${org.orgName}</option>
            </c:forEach>
        </select><br/>
        <button name="submit" type="submit" class="btn btn-primary" > 提交</button>
    </div>
</form>
</body>
</html>
