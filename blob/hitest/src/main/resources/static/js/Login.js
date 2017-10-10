function login() {
    var articleList = "getStrCode.do";
    var first = 0;
    var lenght = 2;
    var array ={};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", loginCheck);
    trReq.send(articleList, array)
}

function loginCheck(data) {
    var username = $("#username").val();
    var password = $("#password").val();
    var authCode = $("#authCode").val();
    if (data.strCode != authCode) {
        $("#login_msg").html("验证码不正确，请重新输入")
    } else {
        loginRes(username, password);
    }

    console.log(data);
}

function loginMouseLeave(count) {
    var username = $("#username").val();
    var password = $("#password").val();
    var authCode = $("#authCode").val();
    if (count == 0) {
        if (username == "") {
            $("#login_msg").html("请输入用户名")
            $("#login_sit").attr("disabled","disabled");
        } else {
            $("#login_msg").html("")
        }
    } else if (count == 1) {
        if (password == "") {
           $("#login_msg").html("请输入密码")
            $("#login_sit").attr("disabled","disabled");
        } else {
            $("#login_msg").html("")
        }
    } else if (count == 2) {
        if(authCode == "") {
            $("#login_msg").html("请出入验证码")
            $("#login_sit").attr("disabled","disabled");
        } else {
            $("#login_msg").html("")
        }
    }
    if (username != "" && password != "" && authCode != null) {
        $("#login_sit").removeAttr("disabled");
    }
}

function loginRes(username, password) {
    var articleList = defaultWebUrl + "user/login";
    var array ={"username": username, "password": password};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", loginResCheck);
    trReq.send(articleList, array)
}

function loginResCheck(data) {
    if (data.res == "success") {
        $("#login_msg").html("登录成功");
        $("#doc-modal-2").modal();
        $("#follow").show();
    } else {
        $("#login_msg").html("登录失败");
        $("#follow").hide();
        chageCode();
    }
}

function loginGetUserinfo() {
    var GetSessionUrl = defaultWebUrl + "user/GetSession"
    $.ajax({
        type:"GET",
        url: GetSessionUrl,
        error:function(data){
            //alert("出错了！！:"+data);
        },
        success:function(data){
            loginGetCheck(data);
        }
    })
}

function loginGetCheck(data) {
    if (data.userinfo != null) {
        console.log("有用户")
    } else {
        console.log("无用户")
    }
}
