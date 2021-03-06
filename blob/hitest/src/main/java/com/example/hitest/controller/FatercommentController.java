package com.example.hitest.controller;

import com.example.hitest.Util.Const;
import com.example.hitest.model.Fatercomment;
import com.example.hitest.service.FatercommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class FatercommentController {

    @Autowired
    FatercommentService fatercommentService;

    @GetMapping(value = "/commentSelect")
    public Map<String, Object> commentSelect(HttpServletRequest request) {

        Map<String, Object> res = new HashMap<String, Object>();
        String content_id = request.getParameter(Const.CONTENT_ID);
        int count = 0;
        String [] keys = new String[1];
        Object [] values = new Object[1];
        keys[0] = Const.ARTICLE_ID;
        values[0] = Long.parseLong(content_id);
        List<Fatercomment> fatercomment = fatercommentService.commentQuery(keys, values);
        /*Fatercomment fatercomment = fatercommentService.byOneQuery(keys, values);*/
        /*for (Fatercomment fatercomment1 : fatercomment) {
            map.put("seq", fatercomment1.getSeq().toString());
            map.put("comment_info", fatercomment1.getComment_info());
            map.put("pid", fatercomment1.getPid().toString());
            map.put("userinfo_id", fatercomment1.getUserinfo_id().toString());
            map.put("article_id", fatercomment1.getArticle_id().toString());
            map.put("comment_time", fatercomment1.getComment_time());
            res.set(count,map);
            count += 1;
        }*/
        for(int i = 0; i < fatercomment.size(); i++) {
            res.put(count+ "", fatercomment.get(i));
            count += 1;
        }
        return res;
    }
}
