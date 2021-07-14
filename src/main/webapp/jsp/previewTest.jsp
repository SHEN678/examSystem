<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/15
  Time: 9:56 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>考试预览</title>

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
            <C:forEach var="exams2" items="${sessionScope.exams2}">
                <p>科目代码：<span>${exams2.courseId}</span></p>  <p>科目名称：<span>${exams2.CName}</span></p> <p>准考证号：<span>No</span></p> <p>考生姓名：<span>Name</span></p>
            </C:forEach>
        </div>
        <div id="leftDown">
<%--            //多选--%>
            <C:forEach var="exams2" items="${sessionScope.exams2}">
                <div><p>${exams2.MDescribe}</p></div>
            </C:forEach>

            <C:forEach var="question" items="${sessionScope.questions}">
                <div align="=center">
                    <p>${question.QNumber}、${question.QStem}</p><br>
                    <label>A、${question.optionA}</label><br>
                    <label>B、${question.optionB}</label><br>
                    <label>C、${question.optionC}</label><br>
                    <label>D、${question.optionD}</label><br>
                    <label><input name="${question.QNumber}" type="checkbox" value="" >A </label>
                    <label><input name="${question.QNumber}" type="checkbox" value="" >B </label>
                    <label><input name="${question.QNumber}" type="checkbox" value="" >C </label>
                    <label><input name="${question.QNumber}" type="checkbox" value="" >D </label>
                </div>
            </C:forEach>
<%--       单选--%>
       <C:forEach var="exams2" items="${sessionScope.exams2}">
                <div>${exams2.SDescribe}</div>
       </C:forEach>
    <C:forEach var="question2" items="${sessionScope.questions2}">
        <div align="=center">
            <p>${question2.QNumber}、${question2.QStem}</p><br>
            <label>A、${question2.optionA}</label><br>
            <label>B、${question2.optionB}</label><br>
            <label>C、${question2.optionC}</label><br>
            <label>D、${question2.optionD}</label><br>
            <label><input name="${question2.QNumber}" type="radio" value="" >A </label>
            <label><input name="${question2.QNumber}" type="radio" value="" >B </label>
            <label><input name="${question2.QNumber}" type="radio" value="" >C </label>
            <label><input name="${question2.QNumber}" type="radio" value="" >D </label>
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
                <td>101</td>
                <td>102</td>
                <td>103</td>
                <td>201</td>
                <td>202</td>
                <td>203</td>
            </tr>
            <tr>
                <td>204</td>
                <td>205</td>
                <td>206</td>
                <td>207</td>
                <td>208</td>
                <td>209</td>
            <tr>
                <td>210</td>
                <td>211</td>
                <td>212</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
        <p>共15题;</p>
        <p>已答5题;</p>
        <p>剩余150题;</p>
        <br>
        <div align="center">

            <button disabled="disabled" style="font-size: 20px" >交卷</button>
        </div>
    </div>

</div>
</body>
</html>
