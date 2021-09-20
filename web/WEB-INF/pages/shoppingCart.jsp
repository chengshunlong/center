<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/11
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>

    <%@include file="/includes/header.jsp"%>

</head>
<body>
    <script>
        $(function () {
            $(".amount").change(function () {
                var eid = $(this).parent().parent().find("td:first").text();
                var amount = this.value;
                location.href="http://localhost:8080/ssm_demo1/shoppingcart/updateAmount?&eid=" + eid + "&amount=" + amount;
            });
        });

    </script>


    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">场地订单</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders">器材订单</a></li>
                </ul>
            </div>
        </div>

        <div class="row">
                <table class="table table-striped">
                    <tr>
                        <td>器材编号</td>
                        <td>器材名称</td>
                        <td>器材单价</td>
                        <td>器材数量</td>
                        <td>器材图片</td>
                        <td>器材总价</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${sessionScope.shoppingCart.items}" var="item">
                        <tr>
                            <td id="eid">${item.value.eid}</td>
                            <td>${item.value.eqpName}</td>
                            <td>${item.value.eqpPrice}</td>
                            <td><input class="amount" type="text" id="itemsAmount" value="${item.value.eqpAmount}" onblur="updateItemsAmount()"></td>
                            <td>${item.value.eqpPicture}</td>
                            <td>${item.value.totalPrice}</td>
                            <td><a href="${pageContext.request.contextPath}/shoppingcart/deleteItem?eid=${item.value.eid}">删除</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <span class="help-block" id="amountReturnInfo"></span>
        </div>

        <div class="row">
            <div class="col-md-10">
                <c:if test="${not empty sessionScope.shoppingCart.items}">
                    <div>
                        <h4>购物车中共有${sessionScope.shoppingCart.items.size()}件商品,
                            总价：${sessionScope.shoppingCart.totalPrice}元</h4>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/shoppingcart/clearShoppingCart">清空购物车</a>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/shoppingcart/payment">付款</a>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="row">
            <c:if test="${requestScope.msg == '购物车中数量不得大于库存'}">
                <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
            </c:if>
        </div>
    </div>
</body>
</html>
