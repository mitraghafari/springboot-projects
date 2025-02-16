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
    <title>welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <jsp:include page="sections/nav.jsp" ></jsp:include>

    <form action="/register" class="form-control">
       <c:if test="${pageContext.request.userPrincipal.name != null}">
           <h2 class="text-center text-danger" type="">
               Welcome ${pageContext.request.userPrincipal.name}
               Welcome ${sessionScope.get("user")}
               <p>

               <a href="/getCars"> call oracleMVC- get cars</a>
               </br>
               <a href="/getCar/2"> call oracleMVC- get  car 2</a>
               </br>
               <a href="/setCar?id=11&model=jk"> call oracleMVC- set  car 2</a>
               </br>
               <a href="/setCar"> call oracleMVC- set  car 1</a>
               </br>
               <a href="/logout" >logout</a>
               </p>
           </h2>
       </c:if>
    </form>
</div>
</body>
</html>
