package com.example.hitest.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;

public class Login_check {

    /**
     * 验证用户是否已经登录
     * @param request
     * @return
     */
    public Boolean loginCheck(HttpServletRequest request){
        String username = request.getSession().getAttribute("username").toString();
        if (StringUtil.isStrNotEmpty(username)) {
            return true;
        }
        return false;
    }

    /**
     * 将登录信息保存下来
     */
    public void loginStateSave(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

    }
}
