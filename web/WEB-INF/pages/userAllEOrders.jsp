<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: livestrong--%>
<%--  Date: 2021/5/10--%>
<%--  Time: 11:14--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>器材订单</title>--%>

<%--    <%@include file="/includes/header.jsp"%>--%>

<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-10">--%>
<%--            <ul class="nav nav-tabs">--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">场地订单</a></li>--%>
<%--                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders">器材订单</a></li>--%>
<%--            </ul>--%>
<%--        </div>--%>

<%--        <div class="col-md-2">--%>
<%--            <ul class="nav nav-tabs">--%>
<%--                <li role="presentation"><a href="${pageContext.request.contextPath}/login/logout">注销</a></li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="row">--%>
<%--        <table class="table table-striped">--%>
<%--            <tr>--%>
<%--                <td>订单流水号</td>--%>
<%--                <td>用户姓名</td>--%>
<%--                <td>器材名称</td>--%>
<%--                <td>器材单价</td>--%>
<%--                <td>器材数量</td>--%>
<%--                <td>订单总价</td>--%>
<%--                <td>订单状态</td>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${pageInfo.list}" var="eorder">--%>
<%--                <tr>--%>
<%--                    <td>${eorder.eoid}</td>--%>
<%--                    <td>${eorder.userName}</td>--%>
<%--                    <td>${eorder.eqpName}</td>--%>
<%--                    <td>${eorder.eqps.eqpPrice}</td>--%>
<%--                    <td>${eorder.eqpAmount}</td>--%>
<%--                    <td>${eorder.totalPrice}</td>--%>
<%--                    <td>${eorder.status}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--    </div>--%>

<%--    <div class="row">--%>
<%--        <h5>共有${pageInfo.pages}页,当前第${pageInfo.pageNum}页</h5>--%>
<%--        <h5>共有${pageInfo.total}条数据</h5>--%>
<%--    </div>--%>

<%--    <div class="row">--%>
<%--        <div class="col-md-6 col-md-offset-4">--%>
<%--            <nav aria-label="Page navigation">--%>
<%--                <ul class="pagination">--%>
<%--                    <li>--%>
<%--                        <a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders?pageNo=1">首页</a>--%>
<%--                    </li>--%>

<%--                    &lt;%&ndash;如果有上一页，就显示&ndash;%&gt;--%>
<%--                    <c:if test="${pageInfo.hasPreviousPage}">--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">--%>
<%--                                <span aria-hidden="true">&laquo;</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </c:if>--%>

<%--                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNo">--%>
<%--                        &lt;%&ndash;navigatepageNums：页数&ndash;%&gt;--%>
<%--                        &lt;%&ndash;页码是否为pageInfo中的当前页码，是就选中&ndash;%&gt;--%>
<%--                        <c:if test="${pageNo == pageInfo.pageNum}">--%>
<%--                            <li class="active"><a href="#">${pageNo}</a></li>--%>
<%--                        </c:if>--%>
<%--                        &lt;%&ndash;不是设置不选中，修改href跳转页面&ndash;%&gt;--%>
<%--                        <c:if test="${pageNo != pageInfo.pageNum}">--%>
<%--                            <li><a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders?pageNo=${pageNo}">${pageNo}</a></li>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>

<%--                    <c:if test="${pageInfo.hasNextPage}">--%>
<%--                        <li>--%>
<%--                            <a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders?pageNo=${pageInfo.pageNum+1}" aria-label="Next">--%>
<%--                                <span aria-hidden="true">&raquo;</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </c:if>--%>

<%--                    <li>--%>
<%--                        &lt;%&ndash;让选中的页码改为总页数（最后一页）&ndash;%&gt;--%>
<%--                        <a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders?pageNo=${pageInfo.pages}">末页</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%--&lt;%&ndash;--%>
<%--<body>--%>
<%--    <ul class="nav nav-tabs">--%>
<%--        <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>--%>
<%--        <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>--%>
<%--        <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>--%>
<%--        <li role="presentation"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>--%>
<%--        <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">场地订单</a></li>--%>
<%--        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders">器材订单</a></li>--%>
<%--    </ul>--%>
<%--<table class="table table-striped">--%>
<%--    <tr>--%>
<%--        <td>订单流水号</td>--%>
<%--        <td>用户姓名</td>--%>
<%--        <td>器材名称</td>--%>
<%--        <td>器材单价</td>--%>
<%--        <td>器材数量</td>--%>
<%--        <td>订单总价</td>--%>
<%--        <td>订单状态</td>--%>
<%--        <td colspan="2">操作</td>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${requestScope.eorders}" var="eorder">--%>
<%--        <tr>--%>
<%--            <td>${eorder.eoid}</td>--%>
<%--            <td>${eorder.userName}</td>--%>
<%--            <td>${eorder.eqpName}</td>--%>
<%--            <td>${eorder.eqps.eqpPrice}</td>--%>
<%--            <td>${eorder.eqpAmount}</td>--%>
<%--            <td>${eorder.totalPrice}</td>--%>
<%--            <td>${eorder.status}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    <a href="${pageContext.request.contextPath}/login/toUserIndex">返回主页</a>--%>

<%--</table>--%>
<%--</body>--%>
<%--</html>--%>
<%--&ndash;%&gt;--%>
