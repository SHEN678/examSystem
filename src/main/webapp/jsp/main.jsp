<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/8
  Time: 3:41 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>主界面</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <style>
        a{
            font-size: 30px;
            color: black;;
            text-decoration:none;

        }
        #datetime{
            display: inline;
        }
        #newExam{
            background-color: dodgerblue;
        }

        #help{
            font-size: 30px;
            margin-left: 50px;
            background-color: dodgerblue;
        }
        .span{
            font-size: 30px;
        }

    </style>
</head>
<body>
<div align="center">
    <h2>XX市职业技能鉴定中心--鉴定站考试管理系统欢迎你！！</h2>
</div>
<div  align="center">
          <a id="newExam" href="${pageContext.request.contextPath}/user/examManage">新建试卷</a>

          <button id="help" onclick="helps()">帮助</button>
    <span>当前时间:</span>
    <div id="datetime" >
        <script>
            setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
        </script>
    </div>
</div>
<p align="center">欢迎您使用鉴定站考试系统 您可以点击<span class="span">新建考试</span>按钮来安排考试，如在使用中遇到问题，请点击<span class="span">帮助</span>来获取帮助</p>
<script>
    function helps() {
        alert("请联系开发人员！！")
    }
</script>
</body>
</html>
