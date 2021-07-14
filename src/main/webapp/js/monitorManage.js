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
    var url = getRootPath() + "/websocket/admin";
    url = url.replace("https", "ws").replace("http", "ws");


    var ws = null;
    var leftTime=$("#leftTime").text()
    var myClock;
    if ('WebSocket' in window){
        ws = new WebSocket(url)
    }else {
        alert("浏览器不支持");
    }

    // 连接安生错误的回调方法
    ws.onerror = function () {
        setMessageInnerHTML("WEBSOCKET发生链接错误");
    }
    // 连接成功的回调方法
    ws.onopen = function (ev) {
        setMessageInnerHTML("WebSocket连接到教师端成功");

    }
    // 收到消息的回调方法
    ws.onmessage = function (ev) {
        // console.log(1)
        setMessageInnerHTML(ev.data);
          // alert("学生端发来消息"+ev.data);//教师端弹出成绩
        var message=ev.data
        var msg=message.split("|");
        if(msg[0]=="JExam"){
            $("#"+msg[1]).css('background-color','green');
            $("#i"+msg[1]).text(msg[2]);
            $("#j"+msg[1]).text("已交卷");
        }else if(msg[0]=="login"){
            console.log(msg[0]);
            console.log[msg[1]];
            $("#"+msg[1]).css('background-color','white');
            $("#j"+msg[1]).text("等待考试");
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

    //开始考试
    $("#beginExam").click(function (){
        layer.confirm('开始考试,请确保已经勾选考生', {
                btn: ['确定','取消'] //按钮
            },function (){

            // alert(leftTime)

            myClock=setInterval(time,1000);//每隔一秒调用time方法
            $("#examType").text("考试中")
            $("#beginExam").attr("disabled","true")//点击一次
            $("#beginExam") .css("background-color","darkgray")//按钮背景颜色
            // alert("是否进行开始考试操作")
            $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框
                $("#j"+($(this).attr("id"))).text("考试中")
                ws.send("beginExam&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
            });
            layer.msg('操作成功', {icon: 1});

            // $("input[name='check']").prop("checked", checked);

         },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })

    });

   function time(){
       $.ajax({
        url:getRootPath()+"/user/countDown",//等同于表单中的action
        data:{
            dTime:leftTime,
        },
        type:"post",
        dataType:"text",
        success:function (data){

            // alert("返回值"+data)
        }
    })
    getDTime(leftTime);
    if(leftTime<=0){
        time.clearInterval(myClock);
    }
    leftTime-=1000;
}

    //时间显示
    function  getDTime(t){
        var hh=0;
        var mm=0;
        var ss=0;
        if(t>0){
            hh=Math.floor(t / 1000/60 / 60 % 24);
            mm=Math.floor(t / 1000 / 60 % 60);
            ss=Math.floor(t / 1000 % 60);
        }
        document.getElementById("time").innerHTML='0'+hh+":"+mm+":"+ss;
    }
    //违纪
    $("#breach").click(function (){

        layer.confirm('确定进行违纪操作？', {
            btn: ['确定','取消'] //按钮
        },function (){

        $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框
            //改样式
            $("#"+($(this).attr("id"))).css('background-color','red');
            $("#j"+($(this).attr("id"))).text("违纪")

            ws.send("breach&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
        });
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })
    //暂停考试
    $("#pauseExam").click(function (){
        layer.confirm('是否进行暂停考试操作？', {
            btn: ['确定','取消'] //按钮
        },function (){
         clearInterval(myClock)
            $("#resumeExam").css("display","block")
            $("#pauseExam").css("display","none")
        // alert("是否进行暂停考试操作")
        $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框
            ws.send("pauseExam&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
        });
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })
    //恢复考试
    $("#resumeExam").click(function (){
        layer.confirm('是否进行恢复考试操作？', {
            btn: ['确定','取消'] //按钮
        },function (){
            $("#resumeExam").css("display","none")
            $("#pauseExam").css("display","block")
        // alert("是否进行恢复考试操作")
            myclock=setInterval(time,1000);//每隔一秒调用time方法
        $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框
            ws.send("resumeExam&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
        });
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })
    //作弊
    $("#cheat").click(function (){

        layer.confirm('确定进行作弊操作？', {
            btn: ['确定','取消'] //按钮
        },function (){

            $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框
                //改样式
                $("#"+($(this).attr("id"))).css('background-color','red');
                $("#j"+($(this).attr("id"))).text("作弊")
                $("#i"+($(this).attr("id"))).text(0);
                ws.send("cheat&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
            });
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })
    //强制交卷
    $("#ForcedExam").click(function (){

        layer.confirm('确定进行强制交卷操作？', {
            btn: ['确定','取消'] //按钮
        },function (){

            $("input:checkbox[name='check']:checked").each(function() { // 遍历name=check的多选框

                ws.send("ForcedExam&&"+($(this).attr("id")));//发送按键type和所有学生准考证号
            });
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })
    //导出成绩
    $("#exportExam").click(function (){
        layer.confirm('确定进行导出成绩操作？', {
            btn: ['确定','取消'] //按钮
        },function (){
            window,location=getRootPath()+"/student/exportScore"
            layer.msg('操作成功', {icon: 1});
        },function (){
            layer.msg('取消操作',{
                time:1000,
            })
        })
    })

});