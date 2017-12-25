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
    <title>Manufacturers & Products</title>
    <style type="text/css">
        td.pad { padding: 10px; }
    </style>
</head>
<body>
<h1>Manufacturers and Products</h1>

<table>
    <tr>
        <td class="pad"><a href="${pageContext.request.contextPath}/showManufacturers"><h3>Manufacturers</h3></a></td>
        <td class="pad"><a href="${pageContext.request.contextPath}/showProducts"><h3>Products</h3></a></td>
    </tr>
</table>

</body>
</html>
