<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<script>
    $.ajax({
        url: "/user/listDept",
        type: "get",
        data: "",
        dataType: "json",
        success: function (data) {
            var html = '<option value="-1">请选择</option>';
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $("#dept").append(html);
        }
    });

</script>
<body>
<%@include file="/html/common/top.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form>
        账号：<input type="text" name="username"><br><br>
        密码：<input type="text" name="">
        部门： <select id="dept" name="deptId"></select>

    </form>

</div>

</body>
</html>
