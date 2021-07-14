<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/15
  Time: 9:57 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>预览考生信息</title>
</head>
<body>
<p align="center" style="font-size: 20px">考生预览</p>
<table border="1" cellspacing="0" width="100%" align="center">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>身份证号</th>
        <th>准考证号</th>
        <th>考试名称</th>
        <th>鉴定等级</th>
    </tr>
        <C:forEach var="student" items="${sessionScope.students}" varStatus="i">
    <tr>
        <td>${i.index+1}</td>
        <td>${student.sname}</td>
        <td>${student.idCard}</td>
        <td>${student.examCardNumber}</td>
        <td>${student.exam.EName}</td>
        <td>${student.exam.ELevel}</td>
    </tr>
    </C:forEach>
</table>
</body>
</html>