<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/16
  Time: 9:44 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生登陆页</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>/js/studentLogin.js"></script>
    <style>
        #panel{
            padding-top: 100px;
            margin: 0 auto;
            border: black 1px solid;
            height: 600px;
            background-color: #9F9F9F;
        }
        span{
            color: red;
        }
    </style>
</head>
<body>
<div id="panel">
    <div align="center">
        <h2>泉州市职业技能鉴定中心-学生考试系统</h2>
    </div>
    <div align="center">
<%--        <form action="${pageContext.request.contextPath}/student/studentLogin" mothod="post" >--%>
            <select style="width:220px;height:30px;"  id="textName">

                <option>请选择考试名称</option>
                <C:forEach var="exams3" items="${sessionScope.exams3}" >
                    <option value="${exams3.EName}">${exams3.EName}</option>
                </C:forEach>
             </select>

            <br>
            <br>
        <input  style="width:220px;height:30px;" type="text" name="examCardNumber" class="examCardNumber" placeholder="请输入准考证号" id="examCardNumber"><br>
        <span id="p_examCardNumber"></span><br>
        <input style="width:220px;height:30px;" type="text" name="idCard" class="idCard" placeholder="请输入身份证号" id="idCard"><br>
        <span id="p_idCard"></span><br>
        <input  style="width:220px;height:30px;" type="text" name="StuName" class="StuName" placeholder="请输入姓名" id="StuName"><br>
        <span id="p_StuName"></span><br>
        <span id="msg">${msg}</span><br>
        <input style="width:80px;height:30px;" id="login" type="button"  value="登录">
<%--        <input style="width:80px;height:30px;" id="reset"type="reset"  value="重置" >--%>
<%--        </form>--%>
    </div>
</div>
</body>
</html>
