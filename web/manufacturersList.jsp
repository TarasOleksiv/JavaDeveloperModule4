<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 19.12.2017
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <CAPTION>List of Manufacturers</CAPTION>
    <tr>
        <td>id</td>
        <td>name</td>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th>${list.id}</th>
            <th>${list.name}</th>
            <th><a href="/showManufacturers?action=update&id=<c:out value="${list.id}"/>">update</a> </th>
            <th><a href="/showManufacturers?action=delete&id=${list.id}">delete</a> </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>