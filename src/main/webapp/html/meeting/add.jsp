<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布会议</title>
</head>
<script>
    $(function () {
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
                $("#deptId").append(html);
            }
        });
    });

    $(function () {
        $("#deptId").change(function () {
            //通过部门id查询该部门下的所有员工
            $.ajax({
                url: "/user/getUserByDeptId",
                type: "get",
                data: {"deptId": $("#deptId").val()},
                dataType: "json",
                success: function (data) {
                    $("#makeUsers").empty();
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        html += '<input type="checkbox" value="'+data[i].id+'" name="makeUsers"/>'+data[i].realName;
                    }
                    $("#makeUsers").append(html);
                }
            });
        });
    });


</script>

<body>
<%@include file="/html/common/top.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">

    <form action="/meeting/addMeeting" method="post">

        标题：<input type="text" name="title"><br><br>

        选择部门：
        <select id="deptId" name="deptId">
        </select><br><br>

        抄送人：
        <div id="makeUsers"> </div>
        <br><br>

        开始时间：<input type="datetime-local" name="startTime"><br><br>
        结束时间：<input type="datetime-local" name="endTime"><br><br>
        会议内容：<textarea rows="5" cols="70" name="content"></textarea><br><br>

        <input type="submit" value="发布">
        <input type="reset" value="重置">
    </form>

</div>

</body>
</html>
