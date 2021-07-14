<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/23
  Time: 1:20 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考试成绩</title>
    <style>
        #panel{
            width:800px ;
            height: 400px;
            /*border: black 1px solid;*/
            margin: 0 auto ;
           margin-top: 100px;
            background-color: #9F9F9F;
        }
        #left{
            width: 49%;
            height: 200px;
            /*border: black 1px solid;*/
            float: left;
        }
        #right{
            width: 49%;
            height: 200px;
            /*border: black 1px solid;*/
            float: right;
        }
    </style>
</head>
<body>
<div id="panel">
<div align="center">
    <h2>考试成绩</h2>
</div>
    <C:forEach var="studentList3" items="${sessionScope.studentList3}">
<div id="left" align="center">
    <p>准考证号：<span>${studentList3.examCardNumber}</span></p>
    <p>考试科目：<span>${studentList3.exam.courseId}</span></p>
    <p>考试工种:<span>${studentList3.exam.EWork}</span></p>
</div>
<div id="right" align="center">
    <p>考生姓名：<span>${studentList3.sname}</span></p>
    <p>科目名称：<span>${studentList3.exam.CName}</span></p>
    <p>等级:<span>${studentList3.exam.ELevel}</span></p>
</div>
    </C:forEach>
<div align="center">
    <p style="font-size: 20px">科目成绩:<span>${score}</span></p>
      <button style="font-size: 20px">完成</button>
</div>
</div>
</body>
</html>
