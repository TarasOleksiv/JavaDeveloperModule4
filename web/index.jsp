<%--
  Created by IntelliJ IDEA.
  User: t.oleksiv
  Date: 19/12/2017
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Manufacturers and Products</h1>

<table>
    <tr><td><a href="${pageContext.request.contextPath}/showManufacturers">Manufacturers</a></td></tr>
    <tr><td><a href="${pageContext.request.contextPath}/products.jsp">Products</a></td></tr>
</table>

</body>
</html>
