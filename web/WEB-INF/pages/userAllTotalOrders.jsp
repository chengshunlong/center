<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/20
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的订单</title>

    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/toUserIndex">主页</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/site/userAllSites">场地租赁</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/eqp/userAllEqps">器材租赁</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/shoppingcart/toShoppingCart">购物车</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/order/toUserAllOrders">场地订单</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders">器材订单</a></li>
            </ul>
        </div>
        <div class="col-md-2">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/logout">注销</a></li>
            </ul>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${pageInfo.list}" var="totalOrder">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row row-no-gutters">
                        <div class="col-md-4">
                            <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;${totalOrder.toid}
                        </div>
                        <div class="col-md-4">
                            <span class="glyphicon glyphicon-time"></span>&nbsp;${totalOrder.time}
                        </div>
                        <div class="col-md-2">
                            <span class="glyphicon glyphicon-yen"></span>&nbsp;${totalOrder.totalPrice}
                        </div>
                        <div class="col-md-2">
                            <span class="glyphicon glyphicon-modal-window"></span>
                            <a href="" id="showDetailsBtn" data-toggle="modal" data-target="#myModal">&nbsp;详情</a>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">订单详情</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-4">
                                        <ul>
                                            <li>订单号</li>
                                            <li>用户编号</li>
                                            <li>账号</li>
                                            <li>姓名</li>
                                            <li>联系电话</li>
                                            <li>总价</li>
                                            <li>时间</li>
                                            <li>状态</li>
                                        </ul>
                                    </div>
                                    <div class="col-md-8">
                                        <ul>
                                            <li>${totalOrder.toid}</li>
                                            <li>${totalOrder.uid}</li>
                                            <li>${totalOrder.userId}</li>
                                            <li>${totalOrder.userName}</li>
                                            <li>${totalOrder.userTel}</li>
                                            <li>${totalOrder.totalPrice}</li>
                                            <li>${totalOrder.time}</li>
                                            <li>${totalOrder.status}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr>
                            <td>器材名称</td>
                            <td>器材单价</td>
                            <td>器材数量</td>
                            <td>器材总价</td>
                        </tr>
                        <c:forEach items="${totalOrder.eOrders}" var="eorder">
                            <tr>
                                <td>${eorder.eqpName}</td>
                                <td>${eorder.eqpPrice}</td>
                                <td>${eorder.eqpAmount}</td>
                                <td>${eorder.totalPrice}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="row" id="page_info">
        <h5>共有${pageInfo.pages}页,当前第${pageInfo.pageNum}页</h5>
        <h5>共有${pageInfo.total}条数据</h5>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-4" id="page_info_nav">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders?pageNo=1">首页</a>
                    </li>

                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNo">

                        <c:if test="${pageNo == pageInfo.pageNum}">
                            <li class="active"><a href="#">${pageNo}</a></li>
                        </c:if>

                        <c:if test="${pageNo != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <a href="${pageContext.request.contextPath}/totalorder/toUserAllTotalOrders?pageNo=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>