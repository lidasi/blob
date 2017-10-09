package com.example.hitest.controller;

import com.example.hitest.Util.Const;
import com.example.hitest.Util.DateUtil;
import com.example.hitest.Util.HttpUtil;
import com.example.hitest.model.Userinfo;
import com.example.hitest.model.Verification;
import com.example.hitest.service.UserinfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class RegisterController {

    @Autowired
    UserinfoService userinfoService;

    @PutMapping(value = "/registerPut")
    public String Register(HttpServletRequest request) {
        String res = "";

        String username = request.getParameter(Const.USERNAME);
        String password = request.getParameter(Const.PASSWORD);
        String email = request.getParameter(Const.EMAIL);
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.USERNAME;
        values[0] = username;
        Userinfo userinfo = userinfoService.queryUsername(keys, values);

        if(userinfo != null) {
            res = "userExist";
        }


        return res;

    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping(value = "/registerPost")
    public Map<String,String> registerPost(HttpServletRequest request) {
        Map<String, String> res = new HashMap<String,String>();
        JSONObject reqParam = HttpUtil.getJsonObjFromRequest(request);
        String username = reqParam.optString("username");
        String password = reqParam.optString("password");
        String email = reqParam.optString("email");
        Date date = new Date();
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.USERNAME;
        values[0] = username;
        Userinfo userinfo = userinfoService.queryUsername(keys, values);
        if (userinfo != null) {
            res.put("res","userExist");
        } else {
            keys[0] = Const.EMAIL;
            values[0] = email;
            Userinfo userinfo1 = userinfoService.queryUsername(keys, values);
            if(userinfo1 != null) {
                res.put("res", "emailExist");
            } else {
                Userinfo useSave = new Userinfo();
                useSave.setUser_name(username);
                useSave.setEmail_code(0L);
                useSave.setUser_password(password);
                useSave.setEmail(email);
                useSave.setCreate_time(DateUtil.getNow17());
                useSave.setUpdata_time(DateUtil.getNow17());
                userinfoService.save(useSave);
                res.put("res", "success");
            }
        }
        return res;
    }

}
