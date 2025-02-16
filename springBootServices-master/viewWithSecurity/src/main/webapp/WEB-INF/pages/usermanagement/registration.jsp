<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>

    <title>register</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form action="/register" class="form-control" method="post">
        <h2 class="text-info">Create your account</h2>
        <input name="username" placeholder="username" type="text" class="form-control">
        <input name="password" placeholder="password" type="password" class="form-control">
        <input name="passConfirm" placeholder="repeat pass" type="password" class="form-control">
        <button  class="btn btn-block " type="submit">create</button>
    </form>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>
