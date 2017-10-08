/**用户名验证**/
function usernameCheck(data) {
    var userCheck = /^[a-zA-Z0-9_-]{4,16}$/;
    if (userCheck.test(data)) {
        return true;
    }
    return false;
}
/**密码验证**/
function passwordCheck(data) {
    var pwdCheck = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
    if (pwdCheck.test(data)) {
        return true;
    }
    return false;
}
/**邮箱验证**/
function emailCheck(data){
    var eamCheck = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (eamCheck.test(data)) {
        return true;
    }
    return false;
}