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
    var examCardNumber=document.getElementById('examCardNumber').innerHTML;

    var url = getRootPath() + "/websocket/"+examCardNumber;
    url = url.replace("https", "ws").replace("http", "ws");


    var ws = null;
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
        // var message =;
        // ws.send(message);
        setMessageInnerHTML("WebSocket连接到学生端成功！");
        ws.send("admin&&"+"login|"+examCardNumber)

    }
    // 收到消息的回调方法
    ws.onmessage = function (ev) {
        // console.log(1)
        setMessageInnerHTML(ev.data);
        if(ev.data=="beginExam"){
            window,location=getRootPath()+"/student/beginExam"
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
});