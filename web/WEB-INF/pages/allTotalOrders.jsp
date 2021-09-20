<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/20
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>器材订单管理</title>
    <%@include file="/includes/header.jsp"%>
    <style>
        ul{
            list-style: none;
        }
    </style>
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
                <li role="presentation"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单管理</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/totalorder/allTotalOrders">器材订单管理</a></li>
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
            <form class="form-inline" action="${pageContext.request.contextPath}/totalorder/searchTotalOrders">
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
                <c:forEach items="${pageInfo.list}" var="totalOrder">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row row-no-gutters">
                                <div class="col-md-4">
                                    <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;${totalOrder.toid}
                                </div>
                                <div class="col-md-3">
                                    <span class="glyphicon glyphicon-time"></span>&nbsp;${totalOrder.time}
                                </div>
                                <div class="col-md-2">
                                    <span class="glyphicon glyphicon-yen"></span>&nbsp;${totalOrder.totalPrice}
                                </div>
                                <div class="col-md-1">
                                    <span class="glyphicon glyphicon-modal-window"></span>
                                    <a href="" id="showDetailsBtn" data-toggle="modal" data-target="#myModal">&nbsp;详情</a>
                                </div>
                                <div class="col-md-2">
                                    <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/totalorder/deleteTotalOrder?toid=${totalOrder.toid}">删除</a>
                                    <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/totalorder/toUpdateTotalOrder?toid=${totalOrder.toid}">修改</a>
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
                        <a href="${pageContext.request.contextPath}/totalorder/allTotalOrders?pageNo=1">首页</a>
                    </li>

                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                                <a href="${pageContext.request.contextPath}/totalorder/allTotalOrders?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNo">

                        <c:if test="${pageNo == pageInfo.pageNum}">
                            <li class="active"><a href="#">${pageNo}</a></li>
                        </c:if>

                        <c:if test="${pageNo != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/totalorder/allTotalOrders?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/totalorder/allTotalOrders?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <a href="${pageContext.request.contextPath}/totalorder/allTotalOrders?pageNo=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>


</div>
</body>
</html>