<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <h2>发生异常！</h2>
    </div>
    <div class="row">
        <div class="page-header">
            <h1>${requestScope.msg}<small></small></h1>
        </div>
    </div>
    <div class="row">
        <samp>错误信息：${requestScope.exceptionInfo}</samp>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${sessionScope.user.userType eq 'admin'}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/login/toAdminIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>返回主页</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/login/toUserIndex"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>返回主页</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
