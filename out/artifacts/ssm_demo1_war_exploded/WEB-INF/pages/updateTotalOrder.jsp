<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/20
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改订单</title>
    <%@include file="/includes/header.jsp"%>
    <script>
        function totalPricec() {
            $.ajax({
                url:"${pageContext.request.contextPath}/totalorder/totalPriceVerify",
                data:{"totalPrice":$("#totalPrice").val()},
                success:function (data) {
                    $("#totalPticeReturnInfo").css("color","red");
                    if(data.toString() == '订单总价合法'){
                        $("#totalPticeReturnInfo").css("color","green");
                    }
                    $("#totalPticeReturnInfo").html(data);
                }
            });
        };
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-md-offset-5">
                <h3>修改订单</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <form class="form-horizontal" action="${pageContext.request.contextPath}/totalorder/updateTotalOrder">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">订单编号</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.toid}</p>
                            <input type="hidden" class="form-control" name="toid"  value="${queriedOrders.toid}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">创建时间</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.time}</p>
                            <input type="hidden" class="form-control" name="time"  value="${queriedOrders.time}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户编号</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.uid}</p>
                            <input type="hidden" class="form-control" name="uid"  value="${queriedOrders.uid}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">用户账号</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.userId}</p>
                            <input type="hidden" class="form-control" name="userId" value="${queriedOrders.userId}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">用户姓名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.userName}</p>
                            <input type="hidden" class="form-control" name="userName" id="userName" value="${queriedOrders.userName}">
<%--                            <span id="userNameReturnInfo" class="row">姓名为2-10位中文字符</span>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userTel" class="col-sm-2 control-label">用户电话</label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${queriedOrders.userTel}</p>
                            <input type="hidden" class="form-control" name="userTel" id="userTel" value="${queriedOrders.userTel}">
<%--                            <span id="userTelReturnInfo" class="row">11位手机号码</span>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="totalPrice" class="col-sm-2 control-label">订单总价</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="totalPrice" maxlength="8" id="totalPrice" value="${queriedOrders.totalPrice}" onblur="totalPricec()">
                            <span id="totalPticeReturnInfo" class="row">最大为999999</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">订单状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="status" id="status">
                                <c:forEach items="${statusList}" var="status">
                                    <option value="${status}" ${status==queriedOrders.status?'selected':''}>${status}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-5">
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <c:if test="${requestScope.errorMsg == '更新失败！请检查输入的数据是否合法'}">
                <div class="alert alert-danger" role="alert">${requestScope.errorMsg}</div>
            </c:if>
        </div>
    </div>

</body>
</html>
