<%--
    Created by IntelliJ IDEA.
    User: ${USER}
Date: ${DATE}
    Time: ${TIME}
        To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>login</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="/login" method="post" class="form-control">
        <h2 class="text-info">Login</h2>

        <input name="username" placeholder="username" type="text" class="form-control">
        <input name="password" placeholder="password" type="password" class="form-control">
        <button value="login" class="btn btn-block " type="submit"  >login</button>

        <h1 class="text-center">

            <p>
            <a href="/register" >Create an account</a>
            </p>
        </h1>
        <h3 class="text-center">
            <p>
            <a href="/checkexc" >To check Trowing customized error</a>
            </p>

        </h3>
    </form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
