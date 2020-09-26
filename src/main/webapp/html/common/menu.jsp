<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单</title>
</head>
<script>
    window.onload = function (ev) {
        //方式1
        // $.ajax({
        //     url: "/menu/listAll",
        //     type: "get",
        //     data: "",
        //     dataType: "json",
        //     success: function (data) {
        //         var html = ""
        //
        //         for (var i = 0; i < data.length; i++) {
        //             var menuParent = data[i];
        //
        //             //说明是一级菜单
        //             if (menuParent.type == 1) {
        //                 html += menuParent.name + '<ul>';
        //
        //                 //说明是二级菜单
        //                 for (var j = 0; j < data.length; j++) {
        //                     var menuSon = data[j];
        //                     if (menuSon.type == 2 && menuSon.parentId == menuParent.id) {
        //                         html += '<li><a href="' + menuSon.url + '">' + menuSon.name + '</a></li>';
        //                     }
        //                 }
        //
        //                 html += '</ul>';
        //             }
        //         }
        //
        //         $("#left").append(html);
        //     }
        // });


        //方式2
        $.ajax({
            url: "/menu/listAll",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var parent = data.parent;
                var son = data.son;

                var html = ""
                for (var i = 0; i < parent.length; i++) {
                    html += parent[i].name + '<ul>';
                    for (var j = 0; j < son.length; j++) {
                        if (parent[i].id == son[j].parentId) {
                            html += '<li><a href="' + son[j].url + '">' + son[j].name + '</a></li>';
                        }
                    }
                    html += '</ul>';
                }
                $("#left").append(html);
            }
        });
    }
</script>

<body>
<div id="left">

    <%--组织管理--%>
    <%--<ul>--%>
    <%--<li><a href="/html/dept/list.jsp">部门管理</a></li>--%>
    <%--<li><a href="/user/list">用户管理</a></li>--%>
    <%--</ul>--%>

    <%--权限管理--%>
    <%--<ul>--%>
    <%--<li><a href="#">角色管理</a></li>--%>
    <%--<li><a href="#">权限管理</a></li>--%>
    <%--</ul>--%>

</div>
</body>
</html>
