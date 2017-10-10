
function dateMourse() {
    var dateMourseUrl = "http://localhost:8080/user/dataMourse"
    var email = $("#email").val();
    var validate_text = $("#validate_text").val();
    var data = {"email": email, "validateText": validate_text}
    var trReq = new jsonReq();
    trReq.setEventListener("receive", validateCheck);
    trReq.send(dateMourseUrl, data);
}
function validateCheck(data) {
    if (data.res == "NoParameter") {
        $("#register_msg").html("验证码不存在，请点击获取邮箱验证码");
    } else if (data.res == "timeExced") {
        $("#register_msg").html("验证码超过30分钟，请重新获取")
    } else if (data.res == "validateError") {
        $("#register_msg").html("验证码不匹配，请重新输入或重新获取后输入新的验证码")
    } else if (data.res == "success") {
        $("#register_msg").html("");
        register();
    } else {
        $("#register_msg").html("未知错误，请联系相关人员");
    }
}

function register(){
    var registerPostUrl = "http://localhost:8080/user/registerPost";
    var username = $("#username").val()
    var password = $("#password").val()
    var email = $("#email").val();
    var data = {"username": username, "password": password, "email": email};
    var trReq = new jsonReq();
    trReq.setEventListener("receive", regist_check);
    trReq.send(registerPostUrl, data)
}

function regist_check(data){
    if(data.res == "userExist") {
        $("#register_msg").html("注册失败,用户名已经存在");
    } else if (data.res == "emailExist") {
        $("#register_msg").html("注册失败,邮箱已经存在");
    } else if (data.res == "success") {
        $("#register_msg").html("注册成功,请及时前往邮箱激活");
    } else {
        $("#register_msg").html("未知错误,请联系站长");
    }

}
var validate = false;
function mouseLeave(data) {
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var validate_text = $("#validate_text").val();
    var useChk = usernameCheck(username);
    var pwdChk = passwordCheck(password);
    var emaChk = emailCheck(email)


    if (data == 0) {
        if (!useChk) {
            $("#register_msg").html("用户名必须是4到16位（字母，数字，下划线，减号）");
            $("#login_sit").attr("disabled","disabled");
        } else {
            $("#register_msg").html("");
        }
    } else if(data == 1) {
        if (!pwdChk) {
            $("#register_msg").html("密码必须是字母加数字组合（不能有特殊符号，下划线）");
            $("#login_rit").attr("disabled","disabled");
        } else {
            $("#register_msg").html("");
        }
    } else if (data == 2) {
        if (!emaChk) {
            $("#register_msg").html("请输入正确的电子邮箱");
            $("#login_rit").attr("disabled","disabled");
        } else {
            $("#register_msg").html("");
        }
    } else if (data == 3) {
        if (validate_text == "") {
            $("#register_msg").html("请输入验证码");
            validate = false;
        } else {
            validate = true;
            $("#register_msg").html("");
        }
    }
    if (useChk && pwdChk && emaChk && validate) {
        $("#login_sit").removeAttr("disabled")
    }

}

function email_validate(){

    var validateVal = $("#email").val();;

    if (validateVal != null || validateVal != "") {
        if(!emailCheck(validateVal)){
            $("#register_msg").html("请输入正确的电子邮箱");
        } else {
            validatePost(validateVal);
            setTime();

        }
    } else {
        $("#register_msg").html("请输入你的邮箱后再获取验证码")
    }
}



function validatePost(email) {
    var registerPostUrl = "http://localhost:8080/user/validatePost";

    var data = {"email": email};

    var trReq = new jsonReq();
    trReq.setEventListener("receive", validateSuc);
    trReq.send(registerPostUrl, data)

}
var count = 60;
function setTime(){
    if (count == 0){
        $("#validate_btn").removeAttr("disabled");
        $("#validate_btn").css("background-color","")
        $("#validate_btn").val("获取邮箱验证码")
        count = 60;
    } else {
        $("#validate_btn").val(count + "秒后才能重新获取")
        $("#validate_btn").css("background-color","#E0E0E0")
        $("#validate_btn").attr("disabled","disabled");

        count--;
        setTimeout(function() {
            setTime()
        },1000)
    }

}

function validateSuc(data) {
    if(data.res == "success") {
        $("#register_msg").html("验证码获取成功");
    } else {
        $("#register_msg").html("验证码获取失败");
    }
}
