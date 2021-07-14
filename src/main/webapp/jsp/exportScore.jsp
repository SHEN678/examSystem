<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/26
  Time: 5:08 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>参考人员总成绩列表</title>
    <style type="text/css">
        #panel{
            margin: 0 auto;
            width: 800px;
        }
        #exportScore{
            text-decoration:none;
            display:block;
             width:150px;
            height: 30px;
            background-color: #9F9F9F;
            color: black;
            font-size: 20px;
            border: black 1px solid;
            float: left;
            margin-left: 250px;
        }
        #cancel{
            text-decoration:none;
            display:block;
            width:150px;
            height: 30px;
            background-color: #9F9F9F;
            color: black;
            font-size: 20px;
            border: black 1px solid;
            margin-left: 180px;
        }
        a{
            display: inline-block;
        }
    </style>
</head>
<body>
<div id="panel">
    <div align="center">
        <h2>参考人员总成绩列表</h2>
    </div>
<div>
    <table border="1" cellspacing="0" width="100%" align="center">
        <tr>
            <th>准考证号</th>
            <th>考生姓名</th>
            <th>考试科目</th>
            <th>科目名称</th>
            <th>工种</th>
            <th>等级</th>
            <th>成绩</th>
        </tr>
        <C:forEach var="ExportScores" items="${sessionScope.ExportScores}" >
            <tr>
                <td>${ExportScores.examCardNumber}</td>
                <td>${ExportScores.sname}</td>
                <td>${ExportScores.courseId}</td>
                <td>${ExportScores.CName}</td>
                <td>${ExportScores.EWork}</td>
                <td>${ExportScores.ELevel}</td>
                <td>${ExportScores.score}</td>
            </tr>
        </C:forEach>
    </table>
    <div  id="button" align="center">
        <br><br>
        <a  id="exportScore"  href= "${pageContext.request.contextPath}/student/exportExcel">导出成绩</a>
        <%--    <input style="width:200px;height:30px;" id="exportScore"  type="button"  value="导出成绩">--%>
<%--        <input style="width:200px;height:30px;" id="cancel"  type="button"  value="取消">--%>
        <a  id="cancel"  href= "#" >取消</a>

        </div>
    </div>
</div>
</div>

</body>
</html>
