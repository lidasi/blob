package com.example.hitest.controller;

import com.example.hitest.Util.Const;
import com.example.hitest.Util.HttpUtil;
import com.example.hitest.model.Userinfo;
import com.example.hitest.service.UserinfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    UserinfoService userinfoService;

    @PostMapping(value = "/login")
    public Map<String, String> Login(HttpServletRequest request, HttpSession session) {
        Map<String, String> res = new HashMap<String, String>();
        JSONObject reParame = HttpUtil.getJsonObjFromRequest(request);
        String username = reParame.optString("username");
        String password = reParame.optString("password");
        String[] keys = new String[2];
        Object[] vlues = new Object[2];
        keys[0] = Const.USERNAME;
        keys[1] = Const.PASSWORD;
        vlues[0] = username;
        vlues[1] = password;
        Userinfo userinfo = userinfoService.queryUsername(keys, vlues);
        if (userinfo != null) {
            session.setAttribute("userinfo", userinfo);
            res.put("username", userinfo.getUser_name());
            res.put("email", userinfo.getEmail());
            res.put("nickname", userinfo.getNickname());
            res.put("sex", userinfo.getSex() + "");
            res.put("head_portrait", userinfo.getHead_portrait());
        } else {
            res.put("res", "error");
        }
        return res;
    }

    @GetMapping(value = "/GetSession")
    public Map<String,Object> GetSession(HttpSession session){
        Map<String, Object> res = new HashMap<String, Object>();
        Object userinfo = session.getAttribute("userinfo");
        res.put("userinfo", userinfo);

        return res;
    }

    @PostMapping(value = "/sessionClean")
    public Map<String, String> sessionClean(HttpSession session) {
        Map<String, String> res = new HashMap<String, String>();
        session.removeAttribute("userinfo");
        res.put("res", "ok");
        return  res;
    }
}
