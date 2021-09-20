<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/10
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加器材订单</title>

    <%@include file="/includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <form action="${pageContext.request.contextPath}/eorder/addEOrder" method="post">
            <div class="form-group">
                <label for="userName">用户姓名</label>
                <input type="text" class="form-control" name="userName" id="userName" required>
            </div>
            <div class="form-group">
                <label for="eqpName">器材名称</label>
                <input type="text" class="form-control" name="eqpName" id="eqpName" required>
            </div>
            <div class="form-group">
                <label for="eqpAmount">器材数量</label>
                <input type="text" class="form-control" name="eqpAmount" id="eqpAmount" required>
            </div>
            <div class="form-group">
                <label for="status">订单状态</label>
                <input type="text" class="form-control" name="status" id="status" required>
            </div>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>


</div>

</body>
</html>
