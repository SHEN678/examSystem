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
    <%--    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>--%>
    <%--    <script type="text/javascript" src="<%=basePath%>/js/manageLogin.js"></script>--%>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body{
            /*background-image: url("timg5.jpg");*/
            /*background-repeat: no-repeat;*/
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
            width: 30%;
            height: 80%;
            margin: 0 auto;
            border-radius: 15px;
            padding: 10px;
            background: rgba(0,0,0,0.1);
        }
        .name,.pwd,.sbm_btn{
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
            width:72%;
            line-height: 35px;
            position: relative;
            margin-bottom: 10px;
        }
        .YZM_code{
            width: 50%;
            height: 35%;
            border: 0;
            border-color: transparent;
            font-size: 16px;
            border-radius: 5px;
            padding-left: 8px;
        }
        .YZM_pic{
            display: block;
            width: 40%;
            height: 35%;
            background-color: #0381ff;
            color: #FFF;
            position: absolute;
            top:0px;
            left: 60%;
            border-right: 5px;
            text-align: center;
        }
        .name{
            margin-top: 20px;
        }
        .sbm_btn{
            text-align: center;
            background-color: #1abc9c;
            color: #FFF;
            line-height: 35px;
        }
        .YZM_error{
            width: 70%;
            color: red;
            font-size: 14px;
            margin: 0 auto;
            height: 20px;
            line-height: 20px;
        }
    </style>
</head>
<body>
<div class="main_bar">
    <div class="login_from">
        <div class="title">
            考试登录系统
        </div>
        <from action="login">

            <div id="form_widgt">
                <input type="text" name="name" class="name" placeholder="请输入账号" id="name1">
                <br>
                <input type="password" name="pwd" class="pwd" placeholder="请输入密码" id="pwd1">

                <p class="yzm">
                    <input type="text" name="code"
                           id="YZM_Input" class="YZM_code" placeholder="验证码">
                    <span id="code" class="YZM_pic" tital="看不清，换一张"></span>
                </p>
                <p class="YZM_error" id="errorTips"></p>

                <%--                <button id="login" type="submit" value="登录"></button>--%>
                <%--                <button id="login"type="reset" value="重置"></button>--%>
                <a href="javascript:;" name="sbm" class="sbm_btn"
                   onclick="return check()">登录</a>
            </div>

        </from>

    </div>

</div>
</body>
</html>
