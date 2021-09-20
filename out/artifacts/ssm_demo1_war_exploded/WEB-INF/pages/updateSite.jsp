<%--
  Created by IntelliJ IDEA.
  User: livestrong
  Date: 2021/5/9
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/includes/header.jsp"%>
    <script>
        function updateSiteNameVerify() {
            $.post({
                url:"${pageContext.request.contextPath}/site/adminUpdateSiteNameVerify",
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

        <%--function updateSitePictureVerify() {--%>
        <%--    $.post({--%>
        <%--        url:"${pageContext.request.contextPath}/site/adminUpdateSitePictureVerify",--%>
        <%--        data:{"sitePicture":$("#sitePicture").val()},--%>
        <%--        success:function (data) {--%>
        <%--            $("#sitePictureReturnInfo").css("color","red");--%>
        <%--            if(data.toString()=="场地图片合法"){--%>
        <%--                $("#sitePictureReturnInfo").css("color","green");--%>
        <%--            }--%>
        <%--            $("#sitePictureReturnInfo").html(data);--%>
        <%--        }--%>
        <%--    });--%>
        <%--};--%>


    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <h3>修改场地信息</h3>
    </div>
    <div class="row">
        <form action="${pageContext.request.contextPath}/site/updateSite" method="post">
            <input type="hidden" name="sid" value="${queriedSite.sid}">
            <div class="form-group">
                <label for="siteName">场地名称</label>
                <input type="text" class="form-control" value="${queriedSite.siteName}" name="siteName" id="siteName" required onblur="updateSiteNameVerify()">
                <span id="siteNameReturnInfo" class="help-block">场地名称长度为2-16位</span>
            </div>
            <div class="form-group">
                <label for="siteStatus">场地状态</label>
                <select class="form-control" name="siteStatus" id="siteStatus">
                    <c:forEach items="${status}" var="status">
                        <option value="${status}" ${status==queriedSite.siteStatus?'selected':''}>${status}</option>
                    </c:forEach>
                </select>
            </div>
<%--            <div class="form-group">--%>
<%--                <label for="sitePicture">场地图片</label>--%>
<%--                <input type="text" class="form-control" value="${queriedSite.sitePicture}" name="sitePicture" id="sitePicture" required onblur="updateSitePictureVerify()">--%>
<%--                <span id="sitePictureReturnInfo" class="help-block">场地图片名称长度为2-40位</span>--%>
<%--            </div>--%>
            <div class="form-group">
                <label for="typeName">场地类型</label>
                <select class="form-control" name="type.typeName" id="typeName">
                    <c:forEach items="${typeNames}" var="name">
                        <option value="${name}" ${name==queriedSite.type.typeName?'selected':''}>${name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-10">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <c:if test="${errorMsg == '修改失败！请检查输入的数据是否合法'}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>
    </div>
</div>
</body>
</html>