  <%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/includes/header.jsp"%>
    <script>
        function addUserIdVerify() {
            $.post({
                url:"${pageContext.request.contextPath}/user/adminAddUserIdVerify",
                data:{"userId":$("#userId").val()},
                success:function (data) {
                    $("#userIdReturnInfo").css("color","red");
                    if(data.toString()=="账号合法"){
                        $("#userIdReturnInfo").css("color","green");
                    }
                    $("#userIdReturnInfo").html(data);
                }
            });
        };

        function addUserPwdVerify() {
            $.post({
                url:"${pageContext.request.contextPath}/user/adminAddUserPwdVerify",
                data:{"userPwd":$("#userPwd").val()},
                success:function (data) {
                    $("#userPwdReturnInfo").css("color","red");
                    if(data.toString() == "密码合法"){
                        $("#userPwdReturnInfo").css("color","green");
                    }
                    $("#userPwdReturnInfo").html(data);
                }
            });
        };

        function addUserNameVerify() {
            $.post({
                url:"${pageContext.request.contextPath}/user/adminAddUserNameVerify",
                data:{"userName":$("#userName").val()},
                success:function (data) {
                    $("#userNameReturnInfo").css("color","red");
                    if(data.toString() == '姓名合法'){
                        $("#userNameReturnInfo").css("color","green");
                    }
                    $("#userNameReturnInfo").html(data);
                }
            });
        };

        function addUserTelVerify() {
            $.post({
                url:"${pageContext.request.contextPath}/user/adminAddUserTelVerify",
                data:{"userTel":$("#userTel").val()},
                success:function (data) {
                    $("#userTelReturnInfo").css("color","red");
                    if(data.toString() == '手机号码合法'){
                        $("#userTelReturnInfo").css("color","green");
                    }
                    $("#userTelReturnInfo").html(data);
                }
            });
        };
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="${pageContext.request.contextPath}/user/addUser" method="post">
            <div class="form-group">
                <label for="userId">用户账号</label>
                <input type="text" class="form-control" name="userId" id="userId" value="${errorAddUser.userId}" required onblur="addUserIdVerify()">
                <span id="userIdReturnInfo" class="help-block">用户账号为6-16为数字、英文或数字英文组合</span>
            </div>
            <div class="form-group">
                <label for="userPwd">用户密码</label>
                <input type="text" class="form-control" name="userPwd" id="userPwd" value="${errorAddUser.userPwd}" required onblur="addUserPwdVerify()">
                <span id="userPwdReturnInfo" class="help-block">密码长度为6-20位</span>
            </div>
            <div class="form-group">
                <label for="userName">用户姓名</label>
                <input type="text" class="form-control" name="userName" id="userName" value="${errorAddUser.userName}" required onblur="addUserNameVerify()">
                <span id="userNameReturnInfo" class="help-block">姓名为2-10位中文字符</span>
            </div>
            <div class="form-group">
                <label for="userTel">用户电话</label>
                <input type="text" class="form-control" name="userTel" id="userTel" value="${errorAddUser.userTel}" required onblur="addUserTelVerify()">
                <span id="userTelReturnInfo" class="help-block">请输入11位手机号</span>
            </div>
            <div class="form-group">
                <label for="userType">用户类型</label>
                <select class="form-control" name="userType" id="userType">
                    <c:forEach items="${requestScope.userTypes}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>

    <div class="row">
        <c:if test="${errorMsg == '添加失败！请检查输入数据是否合法'}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>
    </div>
</div>


</body>
</html>
