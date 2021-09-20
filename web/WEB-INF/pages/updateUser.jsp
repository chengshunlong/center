<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新用户信息</title>

    <%@include file="/includes/header.jsp"%>
    <script>
        function updateUserIdVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/adminUpdateUserIdVerify",
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

        function updateUserPwdVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/adminUpdateUserPwdVerify",
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

        function updateUserNameVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/adminUpdateUserNameVerify",
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

        function updateUserTelVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/adminUpdateUserTelVerify",
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
        <h3>修改用户信息</h3>
    </div>
    <div class="row">
        <form action="${pageContext.request.contextPath}/user/updateUser" method="post">
            <input type="hidden" name="uid" value="${queriedUser.uid}">
            <div class="form-group">
                <label for="userId">用户账号</label>
                <input type="text" class="form-control" value="${requestScope.queriedUser.userId}" name="userId" id="userId" required onblur="updateUserIdVerify()">
                <span id="userIdReturnInfo" class="help-block">用户账号为6-16为数字、英文或数字英文组合</span>
            </div>
            <div class="form-group">
                <label for="userPwd">用户密码</label>
                <input type="text" class="form-control" value="${queriedUser.userPwd}" name="userPwd" id="userPwd" required onblur="updateUserPwdVerify()">
                <span id="userPwdReturnInfo" class="help-block">密码长度为6-20位</span>
            </div>
            <div class="form-group">
                <label for="userName">用户姓名</label>
                <input type="text" class="form-control" value="${queriedUser.userName}" name="userName" id="userName" required onblur="updateUserNameVerify()">
                <span id="userNameReturnInfo" class="help-block">姓名为2-10位中文字符</span>
            </div>
            <div class="form-group">
                <label for="userTel">用户电话</label>
                <input type="text" class="form-control" value="${queriedUser.userTel}" name="userTel" id="userTel" required onblur="updateUserTelVerify()">
                <span id="userTelReturnInfo" class="help-block">电话号码为11位手机号</span>
            </div>
            <div class="form-group">
                <label for="userType">用户类型</label>
                <select class="form-control" name="userType" id="userType">
                    <c:forEach items="${requestScope.userTypes}" var="type">
                        <option value="${type}" ${queriedUser.userType==type?'selected':''}>${type}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>
    <div class="row">
        <c:if test="${errorMsg == '修改失败！请检查输入的数据是否合法'}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>
    </div>
</div>
</body>
</html>
