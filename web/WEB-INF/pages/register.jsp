<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/12
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>

    <%@include file="/includes/header.jsp"%>

    <script>
        function verifyUserId() {
            $.post({
                url:"${pageContext.request.contextPath}/regist/registUserIdVerify",
                data:{"userId":$("#userId").val()},
                success:function (data) {
                    if(data.toString() != ""){
                        $("#userIdReturnInfo").css("color","red");
                        if(data.toString()=="账号合法"){
                            $("#userIdReturnInfo").css("color","green");
                        }
                        $("#userIdReturnInfo").html(data);
                    }
                }
            });
        };

        function verifyUserPwd() {
            $.post({
                url:"${pageContext.request.contextPath}/regist/registUserPwdVerify",
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

        function verifyUserName() {
            $.post({
                url:"${pageContext.request.contextPath}/regist/registUserNameVerify",
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

        function verifyUserTel() {
            $.post({
                url:"${pageContext.request.contextPath}/regist/registUserTelVerify",
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

        <%--$("#submitBtn").click(function () {--%>
        <%--    $.ajax({--%>
        <%--        type:"POST",--%>
        <%--        url:"${pageContext.request.contextPath}/regist/register",--%>
        <%--        dataType:"json",--%>
        <%--        data:$("#registForm").serialize(),--%>
        <%--        success: function (data) {--%>
        <%--            alert(data);--%>
        <%--            window.location.href="/WEB-INF/pages/login.jsp";--%>
        <%--        }--%>
        <%--    });--%>
        <%--});--%>
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form action="${pageContext.request.contextPath}/regist/register" method="post">
                <div class="form-group">
                    <label for="userId">账号</label>
                    <input type="text" class="form-control" name="userId" id="userId" required onblur="verifyUserId()">
                    <span id="userIdReturnInfo" class="help-block">用户名为6-16为数字、英文或数字英文组合</span>
                </div>
                <div class="form-group">
                    <label for="userPwd">密码</label>
                    <input type="password" class="form-control" name="userPwd" id="userPwd" required onblur="verifyUserPwd()">
                    <span id="userPwdReturnInfo" class="help-block">密码长度为6-20位</span>
                </div>
                <div class="form-group">
                    <label for="userName">姓名</label>
                    <input type="text" class="form-control" name="userName" id="userName" required onblur="verifyUserName()">
                    <span id="userNameReturnInfo" class="help-block">姓名为2-10位中文字符</span>
                </div>
                <div class="form-group">
                    <label for="userTel">电话</label>
                    <input type="text" class="form-control" name="userTel" id="userTel" required onblur="verifyUserTel()">
                    <span id="userTelReturnInfo" class="help-block">请输入11位手机号</span>
                </div>
                <div class="form-group">
                    <label for="userType">身份</label>
                    <select class="form-control" name="userType" id="userType">
                        <c:forEach items="${types}" var="type">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>                <a style="float: right" class="btn btn-default" href="${pageContext.request.contextPath}/login/toLogin">已有账号？</a>
            </form>
            <c:if test="${requestScope.msg == '请检查输入字段是否合法'}">
                <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
            </c:if>
        </div>
    </div>
    <div class="row">

    </div>
</div>
</body>
</html>
