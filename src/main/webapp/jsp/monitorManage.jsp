<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/15
  Time: 9:23 下午
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
    <title>监考管理</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/monitorManage.js"></script>
    <script type="text/javascript" src="<%=basePath%>/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css"></link>
    <style>
        #panel{
            margin: 0 auto;
            width: 1000px;
            height: 800px;
        }
        #up{
            height: 60px;
            padding-left: 10px;
            border: black 1px solid;
        }
        #down{
            margin-top: 20px;
            border: black 1px solid;
            height: 720px;
        }
        #img{
            margin: 10px;
            width: 20%;
            height: 100px;
            border: black 1px solid;
            float: left;
            background-color: dodgerblue;
        }
        #title{
            margin: 10px 10px 10px 23%;
            width: 75%;
            height: 100px;
            border: black 1px solid;

        }
        #left{
            margin: 10px;
            width: 20%;
            height: 580px;
            border: black 1px solid;
            float: left;
            background-color: lightblue;
        }
        #right{
            margin: 20px 10px 10px 23%;
            width: 75%;
            height: 580px;
            border: black 1px solid;
            background-color: #9F9F9F;
        }
        .but{

            padding-left: 60px;
        }
        button{
            margin-top: 20px;
            font-size: 20px;
        }
        .examInfo{
            display: inline-block;
            padding-left:10px;
             margin-top: 30px;

        }
        #rightUp{
            padding-left: 5px;
            padding-top: 5px;
            height: 30px;
            border: black 1px solid;
        }
        #rightDown{
            overflow: auto;
        }
        .StuList{
            width: 22%;
            height: 160px;
            margin: 10px;
            float: left;
            border: black 1px solid;
        }
        img{
            width: 40%;
            height: 60px;
        }
    </style>
</head>
<body>
<div id="panel">
    <div id="up">
        <p>泉州市职业技能鉴定中心--鉴定站考试管理系统,<span style="font-size: 20px">欢迎您！</span></p>
    </div>
    <div id="down">
        <div id="img">

        </div>
        <C:forEach var="monExam" items="${sessionScope.monExams}">
        <div id="title">
            <h2 align="center">考试名称:<span>${monExam.EName}</span></h2>
            <p class="examInfo">考试类型:<span>${monExam.EType}</span></p>
            <p class="examInfo">考试时间:<span>${monExam.ETime}</span></p>
            <p class="examInfo">考生人数:<span>${monExam.SNumber}</span></p>
            <p class="examInfo">鉴定级别:<span>${monExam.ELevel}</span></p>
            <p class="examInfo">鉴定工种:<span>${monExam.EWork}</span></p>
        </div>
        </C:forEach>

        <div id="left">
            <div class="but">
                <p>考试状态：</p><br>
                <p id="examType">等待考试</p><br>
                <p>考试时间倒计时</p><br>
                <p id="time">02:0:0</p><br>
            </div>
            <div class="but">
                <input  style="display: block" type="button" class="layui-btn" id="beginExam" name="beginExam" value="开始考试"/>
                <br><br>
                <input  style="display: block" type="button" class="layui-btn" id="ForcedExam" name="ForcedExam" value="强制交卷"/>
                <br><br>
                <input style="display: block" type="button" class="layui-btn" id="cheat" name="cheat"   value="作弊"/>
                <br><br>
                <input style="display: block" type="button" class="layui-btn" id="breach" name="breach"  value="违纪"/>
                <br><br>
                <input style="display: block" type="button" class="layui-btn" id="pauseExam" name="pauseExam"  value="暂停考试"/>
                <input style="display: none " type="button" class="layui-btn" id="resumeExam" name="ResumeExam"  value="恢复考试"/>
                <br><br>
                    <input  style="display: block" type="button" class="layui-btn" id="exportExam" name="exportExam"  value="导出成绩"/>
                <br><br>
                <div  style="display: none">
                    <p id="message"></p>
                </div>
            </div>
        </div>

        <div id="right">
            <div id="rightUp">
                <label>考生列表:</label><label><input name="all" type="checkbox" value=""  >全选/反选</label>
            </div>

            <div id="rightDown">
                <C:forEach var="monStudents" items="${sessionScope.monStudents}" >
                <div class="StuList" id="${monStudents.examCardNumber}">
                    <div align="center" >
                        <input id="${monStudents.examCardNumber}"  name="check" type="checkbox" >
                        <img align="center" src="<%=basePath%>/img/head.jpg" alt="">
                    </div>
                    <div align="center">
                        准考证号:<span >${monStudents.examCardNumber}</span><br>
                        姓名:<span>${monStudents.sname}</span><br>
                        状态:<span id="j${monStudents.examCardNumber}">${monStudents.sstate}</span><br>
                        成绩:<span id="i${monStudents.examCardNumber}">无</span><br>
                    </div>
                </div>
                </C:forEach>
            </div>
        </div>
    </div>
</div>
<p id="leftTime" style="display: none">${sessionScope.dTime}</p>
<script>
    <%--var leftTime=${sessionScope.dTime};--%>
    //全选
    $(function (){
        $("input[name='all']").click(function(){
            $("input[name='check']").prop("checked",this.checked);
        });
    });

</script>
</body>
</html>