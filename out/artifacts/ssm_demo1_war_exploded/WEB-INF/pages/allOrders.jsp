<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>场地订单管理</title>
    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/toAdminIndex">主页</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/user/allUsers">用户信息管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/site/allSites">场地信息管理</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/allEqps">器材信息管理</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单管理</a></li>
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
            <form class="form-inline" action="${pageContext.request.contextPath}/order/searchOrder">
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
                <td>订单流水号</td>
                <td>用户姓名</td>
                <td>场地名称</td>
                <td>订单状态</td>
                <td colspan="3">操作</td>
            </tr>

            <c:forEach items="${pageInfo.list}" var="order">
                <tr>
                    <td>${order.oid}</td>
                    <td>${order.userName}</td>
                    <td>${order.siteName}</td>
                    <td>${order.status}</td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/order/deleteOrder?oid=${order.oid}">删除</a></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/order/toUpdateOrder?oid=${order.oid}">修改</a></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/order/siteApprove?oid=${order.oid}">审批</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="row">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/order/toAddOrder">添加订单</a></td>
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
                        <a href="${pageContext.request.contextPath}/order/allOrders?pageNo=1">首页</a>
                    </li>

                    <%--如果有上一页，就显示--%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/order/allOrders?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/order/allOrders?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/order/allOrders?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <%--让选中的页码改为总页数（最后一页）--%>
                        <a href="${pageContext.request.contextPath}/order/allOrders?pageNo=${pageInfo.pages}">末页</a>
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
    <li role="presentation"><a href="${pageContext.request.contextPath}/user/allUsers">用户信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/site/allSites">场地信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/allEqps">器材信息管理</a></li>
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单信息管理</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/eorder/allEOrders">器材订单信息管理</a></li>
</ul>
<table class="table table-striped">
    <tr>
        <td>订单流水号</td>
        <td>用户姓名</td>
        <td>场地名称</td>
        <td>订单状态</td>
        <td colspan="3">操作</td>
    </tr>
    <c:forEach items="${requestScope.queriedOrders}" var="order">
        <tr>
            <td>${order.oid}</td>
            <td>${order.userName}</td>
            <td>${order.siteName}</td>
            <td>${order.status}</td>
            <td><a href="${pageContext.request.contextPath}/order/deleteOrder?oid=${order.oid}">删除</a></td>
            <td><a href="${pageContext.request.contextPath}/order/toUpdateOrder?oid=${order.oid}">修改</a></td>
            <td><a href="${pageContext.request.contextPath}/order/siteApprove?oid=${order.oid}">审批</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="${pageContext.request.contextPath}/order/toAddOrder">添加器材</a></td>
    </tr>
</table>
</body>
</html>
--%>
