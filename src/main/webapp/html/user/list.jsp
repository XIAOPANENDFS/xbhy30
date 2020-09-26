<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户</title>
</head>
<body>
<%@include file="/html/common/top.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">
    <br>
    <a href="" class="btn btn-success">查询</a>
    <a href="/html/user/add.jsp" class="btn btn-success">添加</a><br>
    <table class="table table-bordered">
        <tr>
            <td>序号</td>
            <td>账号</td>
            <td>性别</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${list}" var="user" varStatus="status">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.sex}</td>
                <td>
                    <a href="/user/toUpdate?id=${user.id}" class="btn btn-primary">修改</a>
                    <a href="/user/delete?id=${user.id}" class="btn btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
