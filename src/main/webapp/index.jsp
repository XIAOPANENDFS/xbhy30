<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script>
    $(function () {
    });

    function changePicCode() {
        $("#picCode").attr("src", "/img/getPicCode?nocache=" + new Date().getTime());
    }

</script>
<body>

<form action="/login/login" method="post">
    用户名：<input type="text" value="admin" name="username" id="username"><br><br>
    密码：<input type="text" value="admin" name="password" id="password"><br><br>
    <input type="checkbox" name="remember" value="1">记住我(7天免登录)<br><br>

    验证码：<input type="text" value="" name="code">
    <img id="picCode" src="/img/getPicCode" alt="加载中" onclick="changePicCode()"/><br><br>

    <input type="submit" value="登录">
</form>

</body>
</html>
