$(function() {
    //身份证号码长度验证
    $("#idCard").blur(function () {
        var idCard = document.getElementsByName('idCard')[0].value;
        var ptn_idCard = /^(^\d{15}$)|(^\d{17}(\d|X)$)/i;
        if (!idCard.match(ptn_idCard)) {
            document.getElementById('p_idCard').innerHTML = '身份证格式不正确';
            return false;
        }
        document.getElementById('p_idCard').innerHTML = '';
        return true;
    });

    $("#login").click(function (){
        var $textName=$("#textName").val();
        var $examCardNumber=$("#examCardNumber").val();
        var $idCard=$("#idCard").val();
        var $StuName=$("#StuName").val();
        $.ajax({
            url:getRootPath()+"/student/studentLogin",//等同于表单中的action
            data:{
                textName:$textName,
                examCardNumber:$examCardNumber,
                idCard:$idCard,
                StuName:$StuName
            },//所要提交的数据
            type:"post",//等同于表单中的method
            dataType:"text",//返回的数据类型
            success:function (data){
                // alert(data)
                if(data==0){
                    alert("请选择考试名称")
                }else if(data==1){
                    alert("有信息为空 请重新填写")
                }else if(data==2){
                    window,location=getRootPath()+"/student/checkInfo"
                }else if(data==4){
                    alert("您作弊被抓了，本场考试禁止登录")
                }
                else{
                    alert("信息填写错误，请重新填写")
                }
            }
        });
    });
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

});