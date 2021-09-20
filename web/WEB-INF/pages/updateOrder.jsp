<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>场地更新</title>

    <%@include file="/includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <h3>修改场地订单信息</h3>
    </div>

    <div class="row">
        <form action="${pageContext.request.contextPath}/order/updateOrder" method="post">
            <input type="hidden" name="oid" value="${queriedOrder.oid}">
            <div class="form-group">
                <label for="userName">用户姓名</label>
                <select class="form-control" name="userName" id="userName">
                    <c:forEach items="${requestScope.users}" var="user">
                        <option value="${user.userName}" ${user.userName==queriedOrder.userName?'selected':''}>${user.userName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="siteName">场地名称</label>
                <select class="form-control" name="siteName" id="siteName">
                    <c:forEach items="${sites}" var="site">
                        <option value="${site.siteName}" ${site.siteName==queriedOrder.siteName?'selected':''}>${site.siteName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="status">订单状态</label>
                <select class="form-control" name="status" id="status">
                    <c:forEach items="${statusList}" var="status">
                        <option value="${status}" ${status==queriedOrder.status?'selected':''}>${status}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>
</div>
</body>
</html>