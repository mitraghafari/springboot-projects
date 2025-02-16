<%--
    Created by IntelliJ IDEA.
    User: ${USER}
Date: ${DATE}
    Time: ${TIME}
        To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>insert car</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-12 ">
            <jsp:include page="../sections/nav.jsp"></jsp:include>
        </div>
    </div>
    <div class="row">

    <form:form class="form-inline" action="save-car" method="post" modelAttribute="carForm">
            <div class="col-sm-4">
                Model:
            </div>
            <div class="col-sm-4">
                <form:input path="model" class="form-control mb-2 mr-sm-2" placeholder="Enter model" id="mdl"/>
            </div>
            <div class="col-sm-4">
                <button type="submit" class="btn btn-primary mb-2">Insert</button>
            </div>
    </form:form>
        </div>

    </tr>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
