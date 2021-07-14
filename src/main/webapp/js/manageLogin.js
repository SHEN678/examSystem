$(function(){
    //账号验证
      $("#account").blur(function (){
          var account = document.getElementsByName('account')[0].value;
          var ptn_username = /^[a-zA-Z0-9]{4,}$/i;
          // 把提示信息清空
          document.getElementById('msg').innerHTML = '';
          if (!account.match(ptn_username)) {
              document.getElementById('p_account').innerHTML = '只能为大小写a~z或者数字0-9且长度不少于4位';
              return false;
          }
          document.getElementById('p_account').innerHTML = '';
          return true;
      });
      //密码验证
      $("#pwd").blur(function (){
        var pwd = document.getElementsByName('pwd')[0].value;
        var ptn_pwd = /^[a-zA-Z0-9]{6,}$/i;
          document.getElementById('msg').innerHTML = '';
        if (!pwd.match(ptn_pwd)) {
            document.getElementById('p_pwd').innerHTML = '只能为大小写a~z或者数字0-9且长度不少于6位';
            return false;
        }
        document.getElementById('p_pwd').innerHTML = '';
        return true;
      });
      //验证码验证
    $("#YZM_Input").blur(function (){
        var code = document.getElementsByName('code')[0].value;
        var ptn_code = /^[a-zA-Z0-9]{4}$/i;
        document.getElementById('msg').innerHTML = '';
        if (!code.match(ptn_code)) {
            document.getElementById('p_code').innerHTML = '只能为大小写a~z或者数字0-9且长度等于4位';
            return false;
        }
        document.getElementById('p_code').innerHTML = '';
        return true;
    });
    // //点击图片刷新
    //   $("#checkCode").click(function (){
    //      var time = new Date();
    //     document.getElementById("checkCode").src ="user/checkCode?time=" + time;
    //   })
    // //重置刷新
    // $("#restart").click(function (){
    //     var time = new Date();
    //     document.getElementById("checkCode").src ="user/checkCode?time=" + time;
    // })
});
