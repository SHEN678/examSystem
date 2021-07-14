$(function() {
    function getRootPath() {
        // 获取当前网址
        var urlPath = window.document.location.href;
        // 获取主机地址之后的目录
        var pathName = window.document.location.pathname;
        var pos = urlPath.indexOf(pathName);
        // 获取主机地址
        var localhostPaht = urlPath.substring(0, pos);
        // 获取带"/"的项目名
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        // 返回完整路径
        return (localhostPaht + projectName);
    }
    var examCardNumber=$("#examCardNumber").text();//准考证号
    var studentName=$("#studentName").text();//学生姓名
    var eNumber=$("#eNumber").text();//试卷编号
    var url = getRootPath() + "/websocket/" + examCardNumber;
    url = url.replace("https", "ws").replace("http", "ws");

    // var examCardNumber=document.getElementById('examCardNumber').innerHTML;
    var ws = null;
    if ('WebSocket' in window){
        ws = new WebSocket(url)
    }else {
        alert("浏览器不支持");
    }
    // 连接发生错误的回调方法
    ws.onerror = function () {
        setMessageInnerHTML("WEBSOCKET发生链接错误");
    }
    // 连接成功的回调方法
    ws.onopen = function (ev) {
        // alert(examCardNumber)
        setMessageInnerHTML("WebSocket连接到学生考试端成功！");
    }
    // 收到消息的回调方法
    ws.onmessage = function (ev) {
        // console.log(1)
        setMessageInnerHTML(ev.data);
        if(ev.data=="breach"){//违纪
            alert("警告：您已经违纪，请注意您的行为！")
        }else if(ev.data=="pauseExam"){//暂停考试
          $("#div").css("display","block")
        }else if(ev.data=="resumeExam"){//恢复考试
            $("#div").css("display","none")
        }else if(ev.data=="ForcedExam"){//强制交卷
            alert("强制交卷，您已无法作答")//没有弹出来
            $.ajax({
                url:getRootPath()+"/student/subExam",
                data:{
                    examCardNumber:examCardNumber,//准考证号
                    studentName:studentName,//学生姓名
                    eNumber:eNumber//试卷编号
                },
                type:"post",//等同于表单中的method
                dataType:"json",//返回的数据类型
                success:function (data){
                    // alert(data);
                    ws.send("admin&&JExam"+"|"+examCardNumber+"|"+data)
                    window,location=getRootPath()+"/student/examGrade"
                    // $(location).attr("href",getRootPath()+"/jsp/examGrade.jsp");
                }
            })
        }else if(ev.data=="cheat"){//作弊
            alert("您作弊被抓了！！")//没有弹出来
            window,location=getRootPath()+"/student/cheat/"+examCardNumber+"/"+eNumber;//带参数
        }

    }
    // 连接关闭的回调方法
    ws.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }
    // 监听窗口关闭事件，防止连接没断关闭窗口。
    window.onbeforeunload = function () {
        closeWebsocket();
    }
    // 将消息显示在网页上
    function setMessageInnerHTML(innerHtml){
        document.getElementById("message").innerHTML += innerHtml + '<br />'
    }
    // 关闭websocket连接
    function closeWebsocket(){
        ws.close();
    }
    // 发送消息
    function send(){
        var message = document.getElementById("msg").value;
        ws.send(message);
    }
    // 提交答案
    //多选题
    // $("input").click(function(){
        $("input[type='checkbox']").click(function(){
            var qNumber=$(this).attr("name");//题号
        // var answer=$(this).attr("class");//答案
        var examCardNumber=$("#examCardNumber").text();//准考证号
        var eNumber=$("#eNumber").text();//试卷编号
        console.log(qNumber+"| "+examCardNumber+"| "+eNumber);
            //更改样式
            if(this.checked == true){
                $("#"+qNumber).css('background-color','green');
            }else if (!$("input[name='" +qNumber + "']").is(":checked")) {
                $("#"+qNumber).css("background-color", "white")
            }

            //更改样式
        // $("#"+qNumber).css('background-color','green');
            var checks = document.getElementsByName(qNumber);
            var chkStr = new Array();
            for(var i = 0;i<checks.length;i++){
                if(checks[i].checked)
                    chkStr.push(checks[i].className);
            }
            console.log(chkStr);
            var chkString = chkStr.join(",");
            console.log(chkString)
            $.ajax({
           url:getRootPath()+"/student/beginAnswer",
           data:{
                qNumber:qNumber,//题号
               examCardNumber:examCardNumber,//准考证号
               eNumber:eNumber,//考试编号
               answer:chkString//答案
           },
                type:"post",//等同于表单中的method
                dataType:"json",//返回的数据类型
                success:function (data){
                }
       })
    })
    //单选题
    $("input[type='radio']").click(function(){
        var qNumber=$(this).attr("name");//题号
        var answer=$(this).attr("class");//答案
        var examCardNumber=$("#examCardNumber").text();//准考证号
        var eNumber=$("#eNumber").text();//试卷编号
        //更改样式
        $("#"+qNumber).css('background-color','green');
        $.ajax({
            url:getRootPath()+"/student/beginAnswer",
            data:{
                qNumber:qNumber,//题号
                examCardNumber:examCardNumber,//准考证号
                eNumber:eNumber,//考试编号
                answer:answer//答案
            },
            type:"post",//等同于表单中的method
            dataType:"json",//返回的数据类型
            success:function (data){
            }
        })
    });

//交卷
    $("#subExam").click(function(){
        // alert(examCardNumber);
        console.log(examCardNumber);
        console.log(studentName);
      $.ajax({
          url:getRootPath()+"/student/subExam",
          data:{
              examCardNumber:examCardNumber,//准考证号
              studentName:studentName,//学生姓名
              eNumber:eNumber//试卷编号
          },
          type:"post",//等同于表单中的method
          dataType:"json",//返回的数据类型
          success:function (data){
              // alert(data);
              ws.send("admin&&JExam"+"|"+examCardNumber+"|"+data)
              window,location=getRootPath()+"/student/examGrade"
              // $(location).attr("href",getRootPath()+"/jsp/examGrade.jsp");

          }
      })

    });


});