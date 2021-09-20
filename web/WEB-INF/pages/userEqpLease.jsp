<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/10
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>器材列表</title>

    <%@include file="/includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>
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
        <table class="table table-striped">
            <tr>
                <td>器材名称</td>
                <td>器材单价</td>
                <td>器材数量</td>
                <td>器材图片</td>
                <td>操作</td>
                <td></td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="eqp">
                <tr>
                    <td>${eqp.eqpName}</td>
                    <td>${eqp.eqpPrice}</td>
                    <td>${eqp.eqpAmount}</td>
                    <td>${eqp.eqpPicture}</td>
                    <td>
                        <c:if test="${eqp.eqpAmount > 0}">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/shoppingcart/addCartItem?eid=${eqp.eid}">加入购物车</a>
                        </c:if>
                        <c:if test="${eqp.eqpAmount <= 0}">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/shoppingcart/addCartItem?eid=${eqp.eid}" disabled="disabled">加入购物车</a>
                        </c:if>
                    </td>
                    <td>${eqp.eqpAmount < 1 ? "库存为空！ " : ""}</td>
                </tr>
            </c:forEach>
        </table>
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
                        <a href="${pageContext.request.contextPath}/eqp/userAllEqps?pageNo=1">首页</a>
                    </li>

                    <%--如果有上一页，就显示--%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/eqp/userAllEqps?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/eqp/userAllEqps?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/eqp/userAllEqps?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <%--让选中的页码改为总页数（最后一页）--%>
                        <a href="${pageContext.request.contextPath}/eqp/userAllEqps?pageNo=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>
</body>
</html>



<%--<body>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">查看场地订单</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/eorder/toUserAllEOrders">查看器材订单</a></li>
</ul>
<table class="table table-striped">
    <tr>
        <td>器材名称</td>
        <td>器材单价</td>
        <td>器材数量</td>
        <td>器材图片</td>
        <td>操作</td>
        <td></td>
    </tr>
    <c:forEach items="${requestScope.queiedEqps}" var="eqp">
        <tr>
            <td>${eqp.eqpName}</td>
            <td>${eqp.eqpPrice}</td>
            <td>${eqp.eqpAmount}</td>
            <td>${eqp.eqpPicture}</td>
            <td><a href="${pageContext.request.contextPath}/shoppingcart/addCartItem?eid=${eqp.eid}">加入购物车</a></td>
            <td>${eqp.eqpAmount < 1 ? "库存为空！" : ""}</td>
        </tr>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">查看购物车</a>
    <a href="${pageContext.request.contextPath}/login/toUserIndex">返回主页</a>
</table>
</body>
</html>--%>
