<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqian
  Date: 2021/6/9
  Time: 2:02 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>

<head>
    <title>考试管理</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css"></link>
    <style>
        #examInfo{
            margin: 0 auto;
            font-size: 20px;
        }
        #left{
            border: 1px black solid;
            width: 49%;
            height: 250px;
            float: left;
        }
        #right{
            border: 1px black solid;
            width: 49%;
            height: 250px;
            float:left;
        }
    </style>
</head>
<body>
      <div align="center">
    <form action="${pageContext.request.contextPath}/user/upload" enctype="multipart/form-data" method="post">
      <p>试卷导入</p>
      <p>试卷压缩包位置:</p>
    <div class="layui-btn-container">
        <input type="button" class="layui-btn layui-btn-normal"  id="choseFile" name="file" value="选择文件"/>

        <input type="button" class="layui-btn" id="doUpload" value="开始上传"/>
    </div>
    </form>
          <label id="fileName"> </label>
      </div>
    <hr>
      <div id="examInfo" align="center">
          <C:forEach var="exam" items="${sessionScope.exams}">
          <p style="font-size: 30px">考试信息</p>
          <div id="left">
              <p>考试名称：<span>${exam.EName}</span></p>
              <br>
              <p>考试时间：<span>${exam.ETime}</span></p>
              <br>
              <p>鉴定工种：<span>${exam.EWork}</span></p>
              <br>
              <p>鉴定等级：<span>${exam.ELevel}</span></p>
              <br>
              <p>考生人数：<span>${exam.SNumber}</span><br></p>
              </div>
              <div id="right">
                  <p>考试试卷：<span>${exam.ENumber}</span></p>
                  <br>
                  <p>考试类型：<span>${exam.EType}</span></p>
                  <br>
                  <p>鉴定机构：<span>${exam.EOrgan}</span></p>
                  <br>
                  <p>及格分数：<span>${exam.EScore}</span></p>
                  <br>
              </div>
          </C:forEach>
              <a id="previewTest" href= "${pageContext.request.contextPath}/user/previewTest" class="layui-btn" target="_blank">预览试卷</a>
             <a id="previewStu" href= "${pageContext.request.contextPath}/user/previewStu" class="layui-btn" target="_blank">预览考生信息</a>
              <br>
              <br>
          <a id="comExamInfo" href= "${pageContext.request.contextPath}/user/monitorManage" class="layui-btn">确认考试信息</a>
      </div>
    <script>
        layui.use(['upload', 'element', 'layer'], function(){
        var $ = layui.jquery
        ,upload = layui.upload
        ,element = layui.element
        ,layer = layui.layer;
        var inputValue;
        var files;
        //选完文件后不自动上传
        upload.render({
        elem: '#choseFile'
        ,url: '${pageContext.request.contextPath}/user/upload'
        ,auto: false
        //,multiple: true
        ,accept: 'file' //普通文件
        ,exts: 'zip'//文件类型
        ,size:'10240'//文件大小
        ,bindAction: '#doUpload'
        ,choose:function (obj){

        layer.prompt({
        formType: 0,
        value: '',
        title: '请输入压缩包解压密码',
        btn: ['确定','取消'], //按钮，
        btnAlign: 'c',
    }, function(value,index){
        layer.close(index);
        inputValue = value;
        console.log(value)
        var oldFiles = obj.pushFile();
        for (let x in oldFiles) {
        delete oldFiles[x];
    }
        var files = obj.pushFile();
        obj.preview(function (index, file, result) {
        console.log(files[index].name);
        $("#fileName").text('文件名称：'+files[index].name);
        layer.msg("加载成功，请点击上传");
    });
    });
    }
        ,before: function(obj){
        layer.load(); //上传loading
        this.data={'zipPwd':inputValue};
    }
        ,done: function(res){
            if(res.code==1){
                layer.msg('上传成功，并解压成功');
                layer.closeAll('loading');
                location.reload();//刷新页面
                for (let x in files) {
                    console.log(files[x].name)
                    delete files[x];
                    console.log(res)
                }
               }else{
                layer.msg('解压密码有误，请重新上传');
                layer.closeAll('loading');
                console.log(res)
            }
        <%--$(location).attr("href", "${pageContext.request.contextPath}/exam/fowInfo")--%>
    }
    });
    });
</script>
</body>
</html>
