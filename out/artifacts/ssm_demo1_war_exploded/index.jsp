<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/8
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.js"></script>
    <script>
        function a(){
            $.post({
                url:"${pageContext.request.contextPath}/a1",
                /*键值对存放
                * 取值是从data的key取，而非表单*/
                data:{"name":$("#username").val()},
                success:function (data) {
                    alert(data);
                }
            })
        }

    </script>
</head>
<body>
    &lt;%&ndash;失去焦点&ndash;%&gt;
    用户名：<input type="text" id="username" onblur="a()">
</body>
</html>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $.post("${pageContext.request.contextPath}/a2",function (data) {
                    console.log(data);
                    var html = "";

                    /*let 使得 i 只在循环内生效*/
                    for (let i = 0; i < data.length; i++) {
                        html += "<tr>" +
                                "<td>" + data[i].userId + "</td>" +
                                "<td>" + data[i].userPwd + "</td>" +
                                "<td>" + data[i].userName + "</td>" +
                                "<td>" + data[i].userTel + "</td>" +
                                "<td>" + data[i].userType + "</td>"
                        + "</tr>"
                    }
                    $("#content").html(html);
                });
            })
        });

    </script>
</head>
<body>
<h1>用户管理</h1>
<input type="button" value="加载数据" id="btn">
<table>
    <tr>
        <td>用户ID</td>
        <td>用户密码</td>
        <td>用户姓名</td>
        <td>用户电话</td>
        <td>用户类型</td>
<%--        <td colspan="2">操作</td>--%>
    </tr>
    <%--<c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.userPwd}</td>
            <td>${user.userName}</td>
            <td>${user.userTel}</td>
            <td>${user.userType}</td>
            <td><a href="${pageContext.request.contextPath}/user/deleteUser?uid=${user.uid}">删除用户</a></td>
            <td><a href="${pageContext.request.contextPath}/user/toUpdateUser?uid=${user.uid}">修改用户信息</a></td>
        </tr>
    </c:forEach>--%>
    <tbody id="content">

    </tbody>
</table>
</body>
</html>
