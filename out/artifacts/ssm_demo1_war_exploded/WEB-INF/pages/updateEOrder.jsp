<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/10
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>器材更新</title>

    <%@include file="/includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <h3>修改器材订单信息</h3>
    </div>

    <div class="row">
        <form action="${pageContext.request.contextPath}/eorder/updateEOrder" method="post">
            <input type="hidden" name="eoid" value="${queriedEOrders.eoid}">
            <div class="form-group">
                <label for="userName">用户姓名</label>
                <input type="text" class="form-control" value="${queriedEOrders.userName}" name="userName" id="userName" required>
            </div>
            <div class="form-group">
                <label for="eqpName">器材名称</label>
                <input type="text" class="form-control" value="${queriedEOrders.eqpName}" name="eqpName" id="eqpName" required>
            </div>
            <div class="form-group">
                <label for="eqpAmount">器材数量</label>
                <input type="text" class="form-control" value="${queriedEOrders.eqpAmount}" name="eqpAmount" id="eqpAmount" required>
            </div>
            <div class="form-group">
                <label for="status">订单状态</label>
                <input type="text" class="form-control" value="${queriedEOrders.status}" name="status" id="status" required>
            </div>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>
</div>
</body>
</html>