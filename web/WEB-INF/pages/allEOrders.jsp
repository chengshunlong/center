<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/10
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>器材订单管理</title>

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
                <li role="presentation"><a href="${pageContext.request.contextPath}/order/allOrders">场地订单管理</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/eorder/allEOrders">器材订单管理</a></li>
            </ul>
        </div>
        <div class="col-md-2">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/login/logout">注销</a></li>
            </ul>
        </div>

    </div>
    <div class="row">
        <table class="table table-striped" id="eordersTable">
            <thead>
                <tr>
                    <td>订单流水号</td>
                    <td>用户姓名</td>
                    <td>器材名称</td>
                    <td>器材数量</td>
                    <td>器材单价</td>
                    <td>订单总价</td>
                    <td>订单状态</td>
                    <td colspan="3">操作</td>
                </tr>
            </thead>
            <tbody>
            </tbody>
            <c:forEach items="${pageInfo.list}" var="eorder">
                <tr>
                    <td>${eorder.eoid}</td>
                    <td>${eorder.userName}</td>
                    <td>${eorder.eqpName}</td>
                    <td>${eorder.eqpAmount}</td>
                    <td>${eorder.eqps.eqpPrice}</td>
                    <td>${eorder.totalPrice}</td>
                    <td>${eorder.status}</td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/eorder/deleteEOrder?eoid=${eorder.eoid}">删除</a></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/eorder/toUpdateEOrder?eoid=${eorder.eoid}">修改</a></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/eorder/eqpApprove?eoid=${eorder.eoid}">审批</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="row">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/eorder/toAddEOrder">添加订单</a></td>
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
                        <a href="${pageContext.request.contextPath}/eorder/allEOrders?pageNo=1">首页</a>
                    </li>

                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/eorder/allEOrders?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="pageNo">

                        <c:if test="${pageNo == pageInfo.pageNum}">
                            <li class="active"><a href="#">${pageNo}</a></li>
                        </c:if>

                        <c:if test="${pageNo != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/eorder/allEOrders?pageNo=${pageNo}">${pageNo}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/eorder/allEOrders?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li>
                        <a href="${pageContext.request.contextPath}/eorder/allEOrders?pageNo=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>
<%--<script>
    $(function () {
        toPageNo(1);
    });

    function toPageNo(pageNo) {
        $.ajax({
            url:"${pageContext.request.contextPath}/eorder/allEOrdersByJson",
            data:"pageNo="+pageNo,
            type:"GET",
            success:function (result) {
                //解析并显示数据
                build_eorders(result);
                //解析并显示分页信息
                build_pageInfo(result);
                //导航条
                build_pageInfo_nav(result);

            }
        });
    }

    
    function build_eorders(result) {
        //每次切换页面会重新append标签，每次都需要清空
        $("#eordersTable tbody").empty();

        var eorders = result.extend.pageInfo.list;
        // 第一个参数是需要遍历的元素    index：索引，item：参数
        $.each(eorders,function (index,item) {
            var eoidTD = $("<td></td>").append(item.eoid);
            var userNameTD = $("<td></td>").append(item.userName);
            var eqpNameTD = $("<td></td>").append(item.eqpName);
            var eqpAmountTD = $("<td></td>").append(item.eqpAmount);
            var eqpPriceTD = $("<td></td>").append(item.eqps.eqpPrice);
            var totalPriceTD = $("<td></td>").append(item.totalPrice);
            var statusTD = $("<td></td>").append(item.status);

            var deleteBtn = $("<a></a>").addClass("btn btn-primary").attr("href","${pageContext.request.contextPath}/eorder/deleteEOrder?eoid=${eorder.eoid}").text("删除");
            var deleteBtnTD = $("<td></td>").append(deleteBtn);
            var updateBtn = $("<a></a>").addClass("btn btn-primary").attr("href","${pageContext.request.contextPath}/eorder/toUpdateEOrder?eoid=${eorder.eoid}").text("修改");
            var updateBtnTD = $("<td></td>").append(updateBtn);
            var approveBtn = $("<a></a>").addClass("btn btn-primary").attr("href","${pageContext.request.contextPath}/eorder/eqpApprove?eoid=${eorder.eoid}").text("审批");
            var approveBtnTD = $("<td></td>").append(approveBtn);
            $("<tr></tr>").append(eoidTD).append(userNameTD).append(eqpNameTD)
            .append(eqpAmountTD).append(eqpPriceTD).append(totalPriceTD)
                .append(statusTD).append(deleteBtnTD).append(updateBtnTD).append(approveBtnTD)
            .appendTo("#eordersTable tbody");
        });
    };

    //解析分页信息
    function build_pageInfo(result) {
        $("#page_info").empty()
        var pageNum = result.extend.pageInfo.pageNum;//当前页码
        var pages = result.extend.pageInfo.pages;//总页码
        var total = result.extend.pageInfo.total;//数据数量
        var pageinfoH5_1 = $("<h5></h5>").text("共有" + pages + "页, 当前第" + pageNum + "页");
        var pageinfoH5_2 = $("<h5></h5>").text("共有" + total + "条数据");
        $("#page_info").append(pageinfoH5_1).append(pageinfoH5_2);
    }

    function build_pageInfo_nav(result) {
        $("#page_info_nav").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLI = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var lastPageLI = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        var prePageLI = $("<li></li>").append($("<a></a>").append("&laquo;"));
        var nextPageLI = $("<li></li>").append($("<a></a>").append("&raquo;"));
        //如果当前为第一页则不显示 前一页
        if(result.extend.pageInfo.hasPreviousPage == false){
            firstPageLI.addClass("disabled");
            prePageLI.addClass("disabled");
        } else {
            //首页
            firstPageLI.click(function () {
                toPageNo(1);
            });
            //前一页
            prePageLI.click(function () {
                toPageNo(result.extend.pageInfo.pageNum - 1);
            });
        }
        if(result.extend.pageInfo.hasNextPage == false){
            lastPageLI.addClass("disabled");
            nextPageLI.addClass("disabled");
        } else {
            //下一
            nextPageLI.click(function () {
                toPageNo(result.extend.pageInfo.pageNum + 1);
            });
            //末页
            lastPageLI.click(function () {
                toPageNo(result.extend.pageInfo.pages);
            });
        }


        //添加 首页和前一页 到ul标签中
        ul.append(firstPageLI).append(prePageLI);
        //遍历添加页码    inde:索引     item:值
        $.each(result.extend.pageInfo.navigatepageNums,function (index,item) {
            var numLI = $("<li></li>").append($("<a></a>").append(item));
            /*如果是当前页码，则添加active*/
            if(result.extend.pageInfo.pageNum == item){
                numLI.addClass("active");
            }
            //绑定单击事件，点击页码跳转
            numLI.click(function () {
                toPageNo(item);
            });
            ul.append(numLI);
        });
        //添加末页和后一页
        ul.append(nextPageLI).append(lastPageLI);

        var pageNav = $("<nav></nav>").append(ul);
        pageNav.appendTo($("#page_info_nav"));
    }
</script>--%>
</body>
</html>
