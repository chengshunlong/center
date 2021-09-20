<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/16
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/includes/header.jsp"%>
    <script>
        function userPwdVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/updateUserPwdVerify",
                data:{"userPwd":$("#userPwd").val()},
                success:function (data) {
                    $("#userPwdReturnInfo").css("color","red");
                    if(data.toString() == '密码合法'){
                        $("#userPwdReturnInfo").css("color","green");
                    }
                    $("#userPwdReturnInfo").html(data);

                }

            });
        };

        function userNameVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/updateUserNameVerify",
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

        function userTelVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/updateUserTelVerify",
                data:{"userTel":$("#userTel").val()},
                success:function (data) {
                    $("#userTelReturnInfo").css("color","red");
                    if(data.toString() == '电话号码合法'){
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
        <div class="col-md-4 col-md-offset-4">
            <form action="${pageContext.request.contextPath}/user/updateBySelf" method="post">
                <div class="form-group">
                    <label for="userId">账号</label>
                    <input class="form-control" id="userId" type="text" name="userId" value="${sessionScope.user.userId}" placeholder="Disabled input here..." readonly>
                </div>
                <div class="form-group">
                    <label for="userPwd">密码</label>
                    <input type="password" class="form-control" value="${sessionScope.user.userPwd}" name="userPwd" id="userPwd" required onblur="userPwdVerify()">
                    <span id="userPwdReturnInfo" class="help-block">密码长度为6-20位</span>
                </div>
                <div class="form-group">
                    <label for="userName">姓名</label>
                    <input type="text" class="form-control" value="${sessionScope.user.userName}" name="userName" id="userName" required onblur="userNameVerify()">
                    <span id="userNameReturnInfo" class="help-block">姓名为2-10位中文字符</span>
                </div>
                <div class="form-group">
                    <label for="userTel">电话</label>
                    <input type="text" class="form-control" value="${sessionScope.user.userTel}" name="userTel" id="userTel" required onblur="userTelVerify()">
                    <span id="userTelReturnInfo" class="help-block">电话号码为11位手机号</span>
                </div>
                <button type="submit" class="btn btn-primary">确定</button>
                <a style="float:right;" class="btn btn-primary" href="${pageContext.request.contextPath}/login/toUserIndex" role="button">取消</a>
            </form>
            <c:if test="${requestScope.msg == '请检查输入字段是否合法'}">
                <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>