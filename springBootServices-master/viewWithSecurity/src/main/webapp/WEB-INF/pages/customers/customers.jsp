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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>insert car</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function removeCustomer(id) {
            if (confirm('are u sure?'))
                window.location = '/deleteCustomer?id=' + id.toString();

        }
        function search(id) {
            window.location='./'

        }
    </script>

</head>
<body>

<div class="container">

    <jsp:include page="../sections/nav.jsp"></jsp:include>

    <form:form class="form-inline" method="post" modelAttribute="custForm">
<%--    <form:form class="form-inline" action="addCust" method="post" modelAttribute="custForm">--%>

        <div class="row">
            <div class="col-3">
                <form:input path="id" placeholder="id" class="form-control"/>
            </div>
            <div class="col-3">
                <form:input path="name" placeholder="name" class="form-control"/>
            </div>
            <div class="col-3">
                <form:input path="family" placeholder="family" class="form-control"/>
            </div>
            <div class="col-3">
                <button type="submit" class="btn btn-primary" name="save"> submit</button>
                <button type="submit" class="btn btn-primary" name="search"> search</button>
            </div>
        </div>
    </form:form>

    <c:if test="${not empty list}" >
    <div class="row">
        <table class="table-hover table-striped">
            <thead>
            <th>id:</th>
                            <th>name:</th>
                            <th>family:</th>
            </thead>
            <tbody>

            <c:forEach items="${list}" var="customer">
                <tr>
                    <form class="form-control" action="/updateCustomer" method="post">
                    <td><input name="id" class="form-control" value="${customer.id}" readonly></td>
                        <td><input name="name" value="${customer.name}" class="form-control"/></td>
                        <td><input name="family" value="${customer.family}" class="form-control"/></td>
                        <td><button type="submit" class="btn btn-primary" >update</button> </td>
                        <td><button type="button" class="btn badge-danger"  onclick="removeCustomer('${customer.id}')">delete</button> </td>
                    </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </c:if>
</div>


<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
