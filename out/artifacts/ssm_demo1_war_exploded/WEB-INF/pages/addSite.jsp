<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/includes/header.jsp"%>
    <script>
        function siteNameVerify() {
            $.ajax({
                url:"${pageContext.request.contextPath}/site/adminAddSiteNameVerify",
                data:{"siteName":$("#siteName").val()},
                success:function (data) {
                    $("#siteNameReturnInfo").css("color","red");
                    if(data.toString()=="场地名称合法"){
                        $("#siteNameReturnInfo").css("color","green");
                    }
                    $("#siteNameReturnInfo").html(data);
                }
            });
        };
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <h3>添加场地</h3>
    </div>
    <div class="row">
        <form action="${pageContext.request.contextPath}/site/addSite" method="post">
            <div class="form-group">
                <label for="siteName">场地名称</label>
                <input type="text" class="form-control" name="siteName" id="siteName" required onblur="siteNameVerify()">
                <span id="siteNameReturnInfo" class="help-block">场地名称长度为2-16位，不能包含特殊字符</span>
            </div>
            <div class="form-group">
                <label for="siteStatus">场地状态</label>
                <select class="form-control" name="siteStatus" id="siteStatus">
                    <c:forEach items="${requestScope.status}" var="status">
                        <option value="${status}">${status}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="typeName">场地类型</label>
                <select class="form-control" name="type.typeName" id="typeName">
                    <c:forEach items="${requestScope.typeNames}" var="name">
                            <option value="${name}"}>${name}</option>
                    </c:forEach>
                </select>
            </div>
            <%--                <input type="text" class="form-control" name="type.typeName" id="typeName" required>--%>

        <%--<div class="form-group">
                <label for="sitePicture">场地图片</label>
                <input type="text" class="form-control" name="sitePicture" id="sitePicture" required>
            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="sitePicture">File input</label>--%>
<%--                <input type="file" id="sitePicture" name="sitePicture">--%>
<%--                <p class="help-block">Example block-level help text here.</p>--%>
<%--            </div>--%>
            <button type="submit" class="btn btn-default">确定</button>
        </form>
    </div>
    <div class="row">
        <c:if test="${errorMsg == '添加失败！请检查输入数据是否合法'}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>
    </div>
</div>

</body>
</html>
