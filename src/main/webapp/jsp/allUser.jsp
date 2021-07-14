<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/8
  Time: 8:53 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<h1>用户列表</h1>
<table>
    <tr>
        <th>编号</th>
        <th>账号</th>
        <th>密码</th>
    </tr>
    <tr>
        <c:forEach var="user" items="${list}">
            <tr>
           <td>${user.userId}</td>
           <td>${user.account}</td>
           <td>${user.pwd}</td>

            </tr>
        </c:forEach>
    </tr>
</table>
</body>
</html>
