<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/8
  Time: 10:16 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>考试登录系统</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/manageLogin.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body{
            /*background-image: url("timg5.jpg");*/
            /*background-repeat: no-repeat;*/
            background-color: powderblue;
        }
        a{
            text-decoration: none;
        }
        .main_bar{
            width: 100%;
            height: 200%;
            margin-top:200px
        }
        .login_from{
            width: 350px;
            height:400px;
            margin: 0 auto;
            border-radius: 15px;
            padding: 10px;
            background: rgba(0,0,0,0.1);
        }
        .account,.pwd{
            display: block;
            width: 70%;
            margin: 0 auto;
            height: 35px;
            font-size: 16px;
            broder-color:transparent;
            border-radius: 5px;
            border: 0;
            padding-left: 8px;
        }
        .yzm{
            height: 35px;
            margin: 0 auto;
            width:70%;
            line-height: 35px;
            position: relative;
            margin-bottom: 10px;
            /*border: black 1px solid;*/
        }
        .YZM_code{
            width: 40%;
            height: 100%;
            font-size: 16px;
            border-radius: 5px;
            float: left;
            padding-left: 8px;
        }
        #checkCode{
            width: 40%;
            height: 100%;
            background-color: #0381ff;
            color: #FFF;
            border-right: 5px;
            float: right;
        }
        .account{
            margin-top: 20px;
        }
        #login,#restart{
            text-align: center;
            background-color: #1abc9c;
            width: 30%;
            color: #FFF;
            line-height: 35px;
        }
        span{
            color: red;
        }
    </style>
</head>
<body>
<div class="main_bar">
    <div class="login_from">
        <div class="title" align="center">
            <h1>考试登录系统</h1>
        </div >
        <form action="${pageContext.request.contextPath}/user/manageLogin" mothod="post">

            <div id="form_widgt" align="center">
                <input type="text" name="account" class="account" placeholder="请输入账号" id="account">
                <span id="p_account"></span><br>
                <input type="password" name="pwd" class="pwd" placeholder="请输入密码" id="pwd">
                <span id="p_pwd"></span><br>

                <p class="yzm">
                   <input type="text" name="code"
                          id="YZM_Input" class="YZM_code" placeholder="验证码">
                    <img src="${pageContext.request.contextPath}/user/checkCode" id="checkCode" onclick="fresh()">
<%--                    <a style="font-style: italic"  href="#" onclick="fresh()">看不清，点击换一张</a><br>--%>
                </p>
                <span id="p_code"></span><br>
                <span id="msg">${msg}</span><br>
                <input id="login" type="submit" value="登录">
                <input id="restart"type="reset"  value="重置" onclick="fresh()">
            </div>

        </form>

    </div>

</div>
<script type="text/javascript">
    //验证码刷新
    function fresh() {
        var time = new Date();
        document.getElementById("checkCode").src = "${pageContext.request.contextPath}/user/checkCode?time=" + time;
        document.getElementById('msg').innerHTML = '';//重置 提示刷新为空
    }
</script>

</body>
</html>
