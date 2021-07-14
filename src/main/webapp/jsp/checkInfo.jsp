<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/16
  Time: 4:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>信息核实</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/checkInfo.js"></script>
    <style>
        #down,#down2{
            width: 63%;
            height: 160px;
            margin-left: 20%;
        }
        #panel{
            margin: 0 auto;
            width: 1000px;
            height: 600px;
            border: black 1px solid;
            background-color:lightskyblue;
        }
        img{

            width: 50px;
            height: 50px;
        }
        #waitExam{
            /*disabled:false;*/
            pointer-events: none;
            color: #9F9F9F;
            /*auto 表示能点击*/
        }
    </style>
</head>
<body>
<div id="panel" >
<div align="center">
    <h2>信息核实</h2>
</div>
<div>
    <div>
        <table border="1" cellspacing="0" width="600px" align="center">
            <C:forEach var="students2" items="${sessionScope.studentList2}">
            <tr>
                <td>准考证号:</td>
                <td id="examCardNumber">${students2.examCardNumber}</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>考生姓名:</td>
                <td>${students2.sname}</td>
                <td>照片:</td>
                <td align="center"><img src="<%=basePath%>/img/head.jpg" alt=""></td>
            </tr>
            <tr>
                <td>身份证号:</td>
                <td>${students2.idCard}</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>考试科目代码:</td>
                <td>${students2.exam.courseId}</td>
                <td>考试科目名称</td>
                <td>${students2.exam.CName}</td>
            </tr>
            <tr>
                <td>考试工种:</td>
                <td>${students2.exam.EWork}</td>
                <td>等级:</td>
                <td>${students2.exam.ELevel}</td>
            </tr>
            </C:forEach>
        </table>
    </div>
    <div>
        <div id="down" >
            <p>&nbsp;&nbsp;&nbsp;&nbsp;请核实上方表格中到信息是否属于个人信息，若信息正确则进入下一步考场信息核实！</p>
            <p>若信息错误请与考场监考员联系！</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;考生请认真核实上方信息是否正确，确定正确之后方可开始考试，如果信息错误就开始</p>
            <p>考试最终没有成绩或者成绩为0，由考生自己负责。</p>
        </div>
        <div id="down1" align="center"><h2>考前须知</h2></div>
        <div id="down2">
            <p>1、请认真核实考生信息</p>
            <p>2、请认真核实考场信息</p>
            <p>3、请遵守考场几率</p>
        </div>
    </div>
    <div align="center">
       <a id="waitExam" href= "${pageContext.request.contextPath}/student/beginExam" type="button">等待考试中...</a>

    </div>
    <div style="display: none">
        <p id="message"></p>
    </div>
</div>
</div>

<%--<script>--%>
<%--    var ws = null;--%>
<%--    if ('WebSocket' in window){--%>
<%--        ws = new WebSocket("ws:localhost:8080/examSystem/websocket/student")--%>
<%--    }else {--%>
<%--        alert("浏览器不支持");--%>
<%--    }--%>
<%--    var connBtn = document.getElementById("connBtn");--%>
<%--    var sendBtn = document.getElementById("sendBtn");--%>
<%--    var closeBtn = document.getElementById("closeBtn");--%>

<%--    // 连接安生错误的回调方法--%>
<%--    ws.onerror = function () {--%>
<%--        setMessageInnerHTML("WEBSOCKET发生链接错误");--%>
<%--    }--%>
<%--    // 连接成功的回调方法--%>
<%--    ws.onopen = function (ev) {--%>
<%--        setMessageInnerHTML("WebSocket连接成功！");--%>
<%--    }--%>
<%--    // 收到消息的回调方法--%>
<%--    ws.onmessage = function (ev) {--%>
<%--        console.log(1)--%>
<%--        setMessageInnerHTML(ev.data);--%>
<%--    }--%>
<%--    // 连接关闭的回调方法--%>
<%--    ws.onclose = function () {--%>
<%--        setMessageInnerHTML("WebSocket连接关闭");--%>
<%--    }--%>
<%--    // 监听窗口关闭事件，防止连接没断关闭窗口。--%>
<%--    window.onbeforeunload = function () {--%>
<%--        closeWebsocket();--%>
<%--    }--%>
<%--    // 将消息显示在网页上--%>
<%--    function setMessageInnerHTML(innerHtml){--%>
<%--        document.getElementById("message").innerHTML += innerHtml + '<br />'--%>
<%--    }--%>
<%--    // 关闭websocket连接--%>
<%--    function closeWebsocket(){--%>
<%--        ws.close();--%>
<%--    }--%>
<%--    // 发送消息--%>
<%--    function send(){--%>
<%--        var message = document.getElementById("msg").value;--%>
<%--        ws.send(message);--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
