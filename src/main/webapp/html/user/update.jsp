<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<script>

</script>
<body>
<%@include file="/html/common/top.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form>
        <input type="text" name="id" id="id" value="${user.id}">
        账号：<input type="text" name="username" id="" value="${user.username}"><br><br>

        部门： <select id="dept" name="deptId">
        <c:forEach items="${list}" var="dept">
            <option value="${dept.id}" <c:if test="${dept.id==user.deptId}">selected</c:if> >${dept.name}</option>
        </c:forEach>
    </select>

    </form>

</div>

</body>
</html>
