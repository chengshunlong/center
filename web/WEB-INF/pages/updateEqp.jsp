<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/includes/header.jsp"%>
    <script>
        function updateEqpNameVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/eqp/adminUpdateEqpNameVerify",
                data:{"eqpName":$("#eqpName").val()},
                success:function (data) {
                    $("#eqpNameReturnInfo").css("color","red");
                    if(data.toString()=="器材名称合法"){
                        $("#eqpNameReturnInfo").css("color","green");
                    }
                    $("#eqpNameReturnInfo").html(data);
                }
            });
        };

        function updateEqpPriceVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/eqp/adminUpdateEqpPriceVerify",
                data:{"eqpPrice":$("#eqpPrice").val()},
                success:function (data) {
                    $("#eqpPriceReturnInfo").css("color","red");
                    if(data.toString()=="器材价格合法"){
                        $("#eqpPriceReturnInfo").css("color","green");
                    }
                    $("#eqpPriceReturnInfo").html(data);
                }
            });
        };

        function updateEqpAmountVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/eqp/adminUpdateEqpAmountVerify",
                data:{"eqpAmount":$("#eqpAmount").val()},
                success:function (data) {
                    $("#eqpAmountReturnInfo").css("color","red");
                    if(data.toString()=="器材数量合法"){
                        $("#eqpAmountReturnInfo").css("color","green");
                    }
                    $("#eqpAmountReturnInfo").html(data);
                }
            });
        };
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <h3>修改器材信息</h3>
    </div>
    <div class="row">
        <form action="${pageContext.request.contextPath}/eqp/updateEqp" method="post">
            <input type="hidden" name="eid" value="${queriedEqp.eid}">
            <div class="form-group">
                <label for="eqpName">器材名称</label>
                <input type="text" class="form-control" value="${queriedEqp.eqpName}" name="eqpName" id="eqpName" required onblur="updateEqpNameVerify()">
                <span id="eqpNameReturnInfo" class="help-block">2-16位非特殊字符</span>
            </div>
            <div class="form-group">
                <label for="eqpPrice">器材价格</label>
                <input type="text" class="form-control" value="${queriedEqp.eqpPrice}" name="eqpPrice" id="eqpPrice" maxlength="8" required onblur="updateEqpPriceVerify()">
                <span id="eqpPriceReturnInfo" class="help-block">0-9999以内的整数</span>
            </div>
            <div class="form-group">
                <label for="eqpAmount">器材数量</label>
                <input type="text" class="form-control" value="${queriedEqp.eqpAmount}" name="eqpAmount" id="eqpAmount" maxlength="8" required onblur="updateEqpAmountVerify()">
                <span id="eqpAmountReturnInfo" class="help-block">0-9999以内的整数</span>
            </div>
<%--            <div class="form-group">--%>
<%--                <label for="eqpPicture">器材图片</label>--%>
<%--                <input type="text" class="form-control" value="${queriedEqp.eqpPicture}" name="eqpPicture" id="eqpPicture" required>--%>
<%--            </div>--%>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>
    <div class="row">
        <c:if test="${requestScope.errorMsg == '更新失败！请检查输入的数据是否合法'}">
            <div class="alert alert-danger" role="alert">${requestScope.errorMsg}</div>
        </c:if>
    </div>
</div>
</body>
</html>