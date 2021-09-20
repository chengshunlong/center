<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/10
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/includes/header.jsp"%>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">购物车</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">场地订单</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders">器材订单</a></li>
                </ul>
            </div>

            <div class="col-md-2">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/login/logout">注销</a></li>
                </ul>
            </div>
        </div>

        <div class="row">
            <c:if test="${empty sessionScope.user}">
                <div class="jumbotron">
                    <h2>Hello, If you already have a membership then login now.</h2>
                    <p>...</p>
                    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/login/toLogin" role="button">Login</a></p>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.user}">
                <div class="jumbotron">
                    <h1>Hello, ${sessionScope.user.userName}!</h1>
                    <p>If you have questions about personal information, You can click here</p>
                    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/user/toUpdateBySelf" role="button"><span class="glyphicon glyphicon-user">UPDATE</span></a></p>
                </div>
            </c:if>
        </div>

    </div>
</body>
</html>
