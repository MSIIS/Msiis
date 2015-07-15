<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="sitemesh-decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录页面</title>
    <style type="text/css">
        body {
            background-image: url(${pageContext.request.contextPath}/resources/view/images/index.jpg);
            background-size: 100% 100%;
            background-repeat: no-repeat;

        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/util/com-submit.js"></script>
    <script type="text/javascript">
        var contextPath = '${pageContext.request.contextPath}';
        $(function () {
            $("#main").css({
                "position": "absolute",
                "top": $(document).height() / 3,
                "left": $(document).width() / 4.5
            });
            var url = contextPath + "/admin/login/submit";
            var successUrl = contextPath + "/admin/index";
            $("#login").attr('action', url);
            $("#login").ajaxForm(function (data) {
                if (!data.success) {
                    alert(data.message);
                } else {
                    location.href = successUrl;
                }
            });

        });
    </script>
</head>
<body background="">

<div class="container" id="main">
    <div class="row" style=" margin:0 auto;height:500px;">


        <div class="col-md-5 col-md-offset-3">
            <form action="" id="login" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">登录名:</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" placeholder="UserName" name="userName">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="Password"
                               name="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="rememberMe" id="remmberMe"> 记住我;)
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                           <div class="col-sm-4">
                            <button type="submit" class="btn btn-primary">登 录</button>
                           </div>
                          <div class="col-sm-8">
                            <label for="reg"><small>没有帐号，弄啥嘞？</small></label>
                                  <a class="btn " href="${pageContext.request.contextPath}/view/user/register" id="reg"><strong>注册帐号</strong></a>
                          </div>
                        </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>