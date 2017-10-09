package com.example.hitest.controller;

import com.example.hitest.Util.Const;
import com.example.hitest.Util.DateUtil;
import com.example.hitest.Util.HttpUtil;
import com.example.hitest.email.EmailUtil;
import com.example.hitest.email.QEmail;
import com.example.hitest.model.Verification;
import com.example.hitest.service.VerificationService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/user")
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    @PostMapping(value = "/validatePost")
    public Map<String, String> validatePost(HttpServletRequest request){
        Map<String, String> res = new HashMap<String,String>();
        JSONObject reqParam = HttpUtil.getJsonObjFromRequest(request);
        String email = reqParam.optString("email");
        int randoms = new Random().nextInt(999999);
        System.out.println(randoms);
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.EMAIL;
        values[0] = email;
        Verification verification = verificationService.queyEmail(keys, values);
        if (verification == null) {
            if (QEmail.qqSender(email, "", randoms, "")) {
                Verification verification1 = new Verification();
                verification1.setEmail(email);
                verification1.setVerificationInfo(randoms);
                verification1.setCreate_time(DateUtil.getNow17());
                verificationService.save(verification1);
                res.put("res", "success");
            } else {
                EmailUtil.sendTextMail(email, randoms);
                Verification verification1 = new Verification();
                verification1.setEmail(email);
                verification1.setVerificationInfo(randoms);
                verification1.setCreate_time(DateUtil.getNow17());
                verificationService.save(verification1);
                res.put("res", "success");
            }
        } else {
            if (QEmail.qqSender(email, "", randoms, "")) {
                verification.setEmail(email);
                verification.setVerificationInfo(randoms);
                verification.setCreate_time(DateUtil.getNow17());
                verificationService.update(verification);
                res.put("res", "success");
            } else {
                EmailUtil.sendTextMail(email, randoms);
                verification.setEmail(email);
                verification.setVerificationInfo(randoms);
                verification.setCreate_time(DateUtil.getNow17());
                verificationService.update(verification);
                res.put("res", "success");
            }



        }
        return res;
    }

    @PostMapping(value = "/dataMourse")
    public Map<String, String> dataMours(HttpServletRequest request){
        Map<String, String> res = new HashMap<>();
        JSONObject reqParam = HttpUtil.getJsonObjFromRequest(request);
        String email = reqParam.optString("email");
        int validateText = reqParam.optInt("validateText");
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.EMAIL;
        values[0] = email;
        Verification verification = verificationService.queyEmail(keys, values);
        if (verification == null) {
            res.put("res", "NoParameter");
        } else if (validateText != verification.getVerificationInfo()){
            res.put("res", "validateError");
        }
        else {
           Long difference = DateUtil.getDistanceTime(DateUtil.getDateCross(verification.getCreate_time()), DateUtil.getDateCross(DateUtil.getNow17()));
           if (difference > 1800) {
               res.put("res", "timeExced");
           } else {
               res.put("res", "success");
           }
        }
        return  res;
    }
}
