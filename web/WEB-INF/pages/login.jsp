<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/3/21
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <%@include file="/includes/header.jsp"%>

    <script>
        function verify01() {
            $.ajax({
                url:"${pageContext.request.contextPath}/login/loginVerify",
                data:{"userId":$("#userId").val(),"userPwd":$("#userPwd").val()},
                success:function (data) {
                    if (data.toString()=="密码正确！"){
                        $("#userIdReturnInfo").css("color","green");
                    } else {
                        $("#userIdReturnInfo").css("color","red");
                    }
                    $("#userIdReturnInfo").html(data);
                }
            });
        };
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form action="${pageContext.request.contextPath}/login/login" method="post">
                <div class="form-group">
                    <label for="userId">账号</label>
                    <input type="text" class="form-control" name="userId" id="userId" required onblur="verify01()">
                    <span id="userIdReturnInfo" class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="userPwd">密码</label>
                    <input type="password" class="form-control" name="userPwd" id="userPwd" required onblur="verify01()">
                </div>
                <button type="submit" class="btn btn-default">确定</button>
                <a style="float: right" class="btn btn-default" href="${pageContext.request.contextPath}/regist/toRegister">没有账号？去注册</a>
            </form>
        </div>
    </div>

    <c:if test="${not empty requestScope.msg}">
        <div class="row">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>${requestScope.msg}</strong>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>
