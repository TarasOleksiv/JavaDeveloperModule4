<%--
  Created by IntelliJ IDEA.
  User: t.oleksiv
  Date: 20/12/2017
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

<form action="<c:url value="/showProducts"/>" method="POST">
    <table border="1">
        <CAPTION>List of Products</CAPTION>
        <tr>
            <th></th>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Manufacturer</th>
        </tr>
        <c:forEach items="${listProducts}" var="listProducts">
            <tr>
                <th><input type="radio" name="productId" value="${listProducts.id}"></th>
                <th>${listProducts.id}</th>
                <th>${listProducts.name}</th>
                <th>${listProducts.price}</th>
                <th>${listProducts.manufacturer.name}</th>
                </tr>
        </c:forEach>
    </table>

    <p></p>

    <table>
        <tr>
            <td><input type="submit" value="Edit" name="Edit"/></td>
            <td><input type="submit" value="Delete" name="Delete"/></td>
        </tr>
    </table>

    <p></p>

    <table>
        <CAPTION>Create new product</CAPTION>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td>Manufacturer:</td>
            <td>
                <select name="manufacturerId">
                    <c:forEach var="listManufacturers" items="${listManufacturers}">
                        <option value="${listManufacturers.id}"><c:out value="${listManufacturers.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add" name="Add"/></td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

</body>
</html>
