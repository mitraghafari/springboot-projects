<%--
    Created by IntelliJ IDEA.
    User: ${USER}
Date: ${DATE}
    Time: ${TIME}
        To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <p>
        Error : contact SB
    </p>
        <p>
    Status: ${pageContext.response.status}: Error:${error} - Message:${requestScope.message}</p> <!-- req attributes  -->
    Status: ${pageContext.response.status}: Error:${error} - Message:${param.message}</p>  <!-- inputs in page  -->

    <!--
    Failed URL: ${pageContext.request.requestURL}
    Exception:  ${pageContext.exception.message}

        <c:forEach items="${pageContext.exception.stackTrace}" var="ste">
         ${ste}
    </c:forEach>
  -->
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
