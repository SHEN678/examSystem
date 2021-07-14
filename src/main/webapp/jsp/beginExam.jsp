<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/20
  Time: 10:53 下午
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
    <title>开始考试</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/beginExam.js"></script>
    <style type="text/css">
        #panel{
            width: 100%;
        }
        #left{
            width: 49%;
            border: black 1px solid;
        }
        #leftUp{
            width: 100%;
            height: 150px;
            border: 1px black solid;
        }
        #right{
            width:49%;
            height: 500px;
            border: 1px black solid;
            position: fixed;
            top:20px;
            right: 10px;
        }
        p{
            display: inline-block;
        }
        #attention{
            padding: 10px;
        }
        label{
            margin: 0 auto;
            align-content: center;
            padding-left: 50px;
        }
        a{
            text-decoration:none;
            border: 1px black solid;
            width: 200px;
            height: 50px;
            background-color: #1abc9c;
        }
        #div{
            width: 100%;
            height: 1000px;
            background-color: #9F9F9F;
            opacity: 30%;
            display: none;
            position: fixed;
            top :0%
        }
        #div2{
            font-size: 100px;
            margin-top: 300px;
        }
    </style>
</head>
<body>
<div id="panel">
    <div id="left">
        <div id="leftUp" align="center">
            <h2>职业技能鉴定国家题库</h2>
            <h2>数控车工高级理论知识试卷QZ001</h2>
        </div>
        <div id="leftCenter" align="center">
            <C:forEach var="studentList2" items="${sessionScope.studentList2}">
                <p>科目代码：<span>${studentList2.exam.courseId}</span></p>
                <p>科目名称：<span>${studentList2.exam.CName}</span></p>
                <p>准考证号：<span id="examCardNumber">${studentList2.examCardNumber}</span></p>
                <p>考生姓名：<span id="studentName">${studentList2.sname}</span></p>
                <p style="display: none">试卷名称:<span id="eNumber">${studentList2.exam.ENumber}</span></p>

            </C:forEach>
        </div>
        <div id="leftDown">
            <%--            //多选--%>
            <C:forEach var="exams4" items="${sessionScope.exams4}">
                <div><p>${exams4.MDescribe}</p></div>
            </C:forEach>

            <C:forEach var="questions3" items="${sessionScope.questions3}">
                <div align="=center">
                    <p>${questions3.QNumber}、${questions3.QStem}</p><br>
                    <label>A、${questions3.optionA}</label><br>
                    <label>B、${questions3.optionB}</label><br>
                    <label>C、${questions3.optionC}</label><br>
                    <label>D、${questions3.optionD}</label><br>
                    <label><input name="${questions3.QNumber}" type="checkbox" class="A" >A</label>
                    <label><input name="${questions3.QNumber}" type="checkbox" class="B" >B</label>
                    <label><input name="${questions3.QNumber}" type="checkbox" class="C" >C</label>
                    <label><input name="${questions3.QNumber}" type="checkbox" class="D" >D</label>
                </div>
            </C:forEach>
<%--           //单选--%>
            <C:forEach var="exams4" items="${sessionScope.exams4}">
                <div>${exams4.SDescribe}</div>
            </C:forEach>
            <C:forEach var="questions4" items="${sessionScope.questions4}">
                <div align="=center">
                    <p>${questions4.QNumber}、${questions4.QStem}</p><br>
                    <label>A、${questions4.optionA}</label><br>
                    <label>B、${questions4.optionB}</label><br>
                    <label>C、${questions4.optionC}</label><br>
                    <label>D、${questions4.optionD}</label><br>
                    <label><input name="${questions4.QNumber}" type="radio" class="A" >A</label>
                    <label><input name="${questions4.QNumber}" type="radio" class="B" >B</label>
                    <label><input name="${questions4.QNumber}" type="radio" class="C" >C</label>
                    <label><input name="${questions4.QNumber}" type="radio" class="D" >D</label>
                </div>
            </C:forEach>

        </div>
    </div>

    <div id="right">

        <p>注意事项</p><br>
        <div id="attention">
            <span>1、本试卷依据2005年颁布的《数控车工》国家职业标准命制，考试时间：120分钟。</span>
            <span>2、请在考试之前认真核实自己的姓名、准考证号和所在的单位名称。</span><br>
            <span>3、请仔细阅读答题要求，在规定位置填写答案。</span>
        </div>
        <p>剩余时间：<span>45:00</span></p>
        <p>题目导航栏</p>
        <table border="1" cellspacing="0" width="600px" align="center">
            <tr>
                <C:forEach var="questions3" items="${sessionScope.questions3}">
                <td id="${questions3.QNumber}">${questions3.QNumber}</td>
                </C:forEach>

                <C:forEach var="questions4" items="${sessionScope.questions4}" >
                <td id="${questions4.QNumber}">${questions4.QNumber}</td>
                </C:forEach>
            </tr>
        </table>
        <p>共15题;</p>
        <p>已答5题;</p>
        <p>剩余150题;</p>
        <br>
        <div align="center">
            <input style="width:150px;height:30px;" id="subExam"  type="button"  value="交卷">
<%--            <button id="subExam"  class="layui-btn" >交卷</button>--%>
        </div>
        <div style="display: none" >
            <p id="message"></p>
        </div>
    </div>

</div>

<div id="div">
    <div id="div2" align="center">
        暂停考试
    </div></div>
</body>
</html>