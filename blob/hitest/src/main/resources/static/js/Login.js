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
    if (data != null) {
        var username = data.username;
        var email = data.email;
        var nickname = data.nickname;
        var sex = data.sex;
        var head_portrait = data.head_portrait;
        $("#login_model").hide();
        $("#userImageUrl").show();
        $("#infoPage_nickName").html(nickname);
        if(head_portrait != null && head_portrait != "") {
            $("#userImageUrl").attr('src', head_portrait);
            $("#info_haed").attr('src', head_portrait);
        } else {
            if (sex == 0) {
                $("#userImageUrl").attr('src',"headImage/defHead_man.jpg");
                $("#info_haed").attr('src', "headImage/defHead_man.jpg")
            } else {
                $("#userImageUrl").attr('src', "headImage/defHead_wman.jpg");
                $("#info_haed").attr('src', "headImage/defHead_wman.jpg");
            }

        }

        $("#login_msg").html("登录成功");
        $("#doc-modal-2").modal();
        $("#follow").show();
        chageCode();
    } else {
        $("#login_msg").html("登录失败");
        $("#follow").hide();
        $("#userImageUrl").hide();
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
        var username = data.userinfo.username;
        var email = data.userinfo.email;
        var nickname = data.userinfo.nickname;
        var sex = data.userinfo.sex;
        var head_portrait = data.userinfo.head_portrait;
        $("#login_model").hide();
        $("#userImageUrl").show();
        $("#infoPage_nickName").html(nickname);
        if(head_portrait != null && head_portrait != "") {

            $("#userImageUrl").attr('src', head_portrait);
            $("#info_haed").attr('src', head_portrait);
        } else {
            if (sex == 0) {
                $("#userImageUrl").attr('src',"headImage/defHead_man.jpg");
                $("#info_haed").attr('src', "headImage/defHead_man.jpg")
            } else {
                $("#userImageUrl").attr('src', "headImage/defHead_wman.jpg");
                $("#info_haed").attr('src', "headImage/defHead_wman.jpg");
            }

        }
        $("#follow").show();
    } else {
        $("#follow").hide();
        $("#login_model").show();
        $("#userImageUrl").hide();
    }
}

function signOut(){
    var sessionCleanUrl = defaultWebUrl + "user/sessionClean"
    var array ={};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", signOutCheck);
    trReq.send(sessionCleanUrl, array)
}

function signOutCheck(data){
    if (data.res == "ok") {
        $("#follow").hide();
        $("#login_model").show();
        $("#userImageUrl").hide();
        location.reload()
    }
}