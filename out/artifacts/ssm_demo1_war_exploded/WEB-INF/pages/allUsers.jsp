<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/8
  Time: 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>

    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/toAdminIndex">主页</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/user/allUsers">用户信息管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/site/allSites">场地信息管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/allEqps">器材信息管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/totalorder/allTotalOrders">器材订单管理</a></li>
            </ul>
        </div>
        <div class="col-md-2">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/logout">注销</a></li>
            </ul>
        </div>
    </div>

    <div class="row row-top">
        <div class="col-md-offset-8">
            <form class="form-inline" action="${pageContext.request.contextPath}/user/searchUser">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
                        <input type="text" class="form-control" name="searchParam" id="exampleInputAmount" placeholder="请输入关键字">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>


    <div class="row">
        <table class="table table-striped">
            <tr>
                <td>用户ID</td>
                <td>用户密码</td>
                <td>用户姓名</td>
                <td>用户电话</td>
                <td>用户类型</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="user">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userPwd}</td>
                    <td>${user.userName}</td>
                    <td>${user.userTel}</td>
                    <td>${user.userType}</td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/user/deleteUser?uid=${user.uid}">删除用户</a></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/user/toUpdateUser?uid=${user.uid}">修改用户</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="row">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/toAddUser">添加用户</a></td>
    </div>

    <div class="row">
        <h5>共有${pageInfo.pages}页,当前第${pageInfo.pageNum}页</h5>
        <h5>共有${pageInfo.total}条数据</h5>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-4">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/allUsers?pageNo=1">首页</a>
                    </li>

                    <%--如果有上一页，就显示--%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/allUsers?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNo">
                        <%--navigatepageNums：页数--%>
                        <%--页码是否为pageInfo中的当前页码，是就选中--%>
                        <c:if test="${pageNo == pageInfo.pageNum}">
                            <li class="active"><a href="#">${pageNo}</a></li>
                        </c:if>
                        <%--不是设置不选中，修改href跳转页面--%>
                        <c:if test="${pageNo != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/user/allUsers?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/allUsers?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <%--让选中的页码改为总页数（最后一页）--%>
                        <a href="${pageContext.request.contextPath}/user/allUsers?pageNo=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
<%--
<body>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="${pageContext.request.contextPath}/login/toAdminIndex">主页</a></li>
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/user/allUsers">用户信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/site/allSites">场地信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/allEqps">器材信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/eorder/allEOrders">器材订单信息管理</a></li>
</ul>
<h1>用户管理</h1>
<table class="table table-striped">
    <tr>
        <td>用户ID</td>
        <td>用户密码</td>
        <td>用户姓名</td>
        <td>用户电话</td>
        <td>用户类型</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.userPwd}</td>
            <td>${user.userName}</td>
            <td>${user.userTel}</td>
            <td>${user.userType}</td>
            <td><a href="${pageContext.request.contextPath}/user/deleteUser?uid=${user.uid}">删除用户</a></td>
            <td><a href="${pageContext.request.contextPath}/user/toUpdateUser?uid=${user.uid}">修改用户信息</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="${pageContext.request.contextPath}/user/toAddUser">添加用户</a></td>
    </tr>
</table>
</body>
</html>
--%>
