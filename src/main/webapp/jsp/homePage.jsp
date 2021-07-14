<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/16
  Time: 4:45 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>传一无纸化考试系统</title>
    <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css"></link>
    <style>
        #panel{
            width: 100%;
            height: 1000px;
            background-color: papayawhip;
        }
   #div{
       padding-top: 300px;

   }
   #div2{
       padding-top: 30px;
   }
    </style>
</head>
<body>
<div id="panel">
<div id="div">
<h1 style="font-weight: bold" align="center">传一无纸化考试系统</h1>
</div>
<div  id="div2" align="center">
<a id="stuExam" href= "${pageContext.request.contextPath}/student/stuLogin" class="layui-btn" target="_blank">学生考试端</a>
<a id="manage" href= "${pageContext.request.contextPath}/user/homePage" class="layui-btn" target="_blank">管理服务端</a>
</div>
</div>
</body>
</html>
