<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>top</title>
</head>
<style>
    #top {
        border: 1px solid red;
        height: 10%;
    }

    #left {
        border: 1px solid red;
        width: 10%;
        height: 88%;
        float: left;
    }

    #right {
        border: 1px solid red;
        margin-left: 5px;
        width: 89%;
        height: 88%;
        float: left;
    }

</style>
<script>
    $(function () {

        $("#detail-img").click(function () {
            // 点击图片时触发文件表单控件
            $("#picFile").click();
        });

        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            // 上传图片
            $.ajax({
                url: "/img/updatePic",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                data: formData,
                type: "post",
                dataType: "json",
                async: true,
                success: function (data) {
                    if (data.code == '200') {
                        //成功
                        $("#detail-img").attr("src", "/img/getHead?pic=" + data.msg);
                    } else {
                        alert(data.msg);
                    }
                }
            });

        });
    });

</script>
<body>

<div id="top">
    <%--<img src="d://2.jpg" alt="加载中">--%>
    <%--<img src="/img/getHead?pic=d:\\2.jpg?pic=d:\\2.jpg" alt="加载中" />--%>
    <img id="detail-img" src="/img/getHead?pic=${loginUser.pic}" alt="加载中"/>

    <!-- 真正的头像图片上传表单 -->
    <input type="file" id="picFile" style="display: none;">

    <%--<form action="/img/updatePic" method="post" enctype="multipart/form-data">--%>
    <%--<input type="file" name="filename"><br>--%>
    <%--<input type="submit" value="修改头像"><br>--%>
    <%--</form>--%>

</div>
</body>
</html>
