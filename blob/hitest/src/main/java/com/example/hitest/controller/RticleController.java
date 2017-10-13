package com.example.hitest.controller;

import com.example.hitest.Util.Const;
import com.example.hitest.Util.DateUtil;
import com.example.hitest.Util.HttpUtil;
import com.example.hitest.model.Content;
import com.example.hitest.service.ContentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class RticleController {

    @Autowired
    private ContentService contentService;

    /**
     * 带分页查询文章
     * @param request
     * @return lidasi
     * @throws IOException
     */
    @PostMapping(value = "/articleList")
    public Map<String, Object> article(HttpServletRequest request) throws IOException {
        JSONObject reqParam = HttpUtil.getJsonObjFromRequest(request);
        int first = reqParam.optInt("first");
        int lenght = reqParam.optInt("lenght");
        int count = 0;
        Map<String, Object> articleMap = new HashMap<String, Object>();
        List<Content> articleList = new ArrayList<Content>();
        articleList = contentService.listAll(first, lenght);
        int allCount = contentService.listAllCnt();
        for(Content articles : articleList) {
           articleMap.put("content_id" + count, articles.getContent_id());
           articleMap.put("title" + count, articles.getTitle_content());
           articleMap.put("content" + count, articles.getContents());
           articleMap.put("code" + count, articles.getCodes());
           articleMap.put("imgUrl" + count, articles.getImgUrl());
           articleMap.put("crTime" + count, DateUtil.getDateShort(articles.getCreate_time()));
           count += 1;
        }
        articleMap.put("pageCount", allCount);
        return articleMap;

    }

    /**
     * 查询一篇文章
     * @param request
     * @return lidasi
     * @throws IOException
     */
    @PostMapping(value = "/titleByOneUrl")
    public Map<String, Object> titleByOne(HttpServletRequest request) throws IOException {
        JSONObject reqParam = HttpUtil.getJsonObjFromRequest(request);
        String reArray = reqParam.optString("titleRes");
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.TITLE_CONTENT;
        values[0] = reArray;

        Map<String, Object> titleByOneAllmap = new HashMap<String, Object>();
        List<Content> titleByOneList = new ArrayList<Content>();
        titleByOneList = contentService.titleByOneAll(keys, values);
        for(Content articles : titleByOneList) {
            titleByOneAllmap.put(Const.CONTENT_ID, articles.getContent_id());
            titleByOneAllmap.put(Const.TITLE, articles.getTitle_content());
            titleByOneAllmap.put(Const.CONTENT, articles.getContents());
            titleByOneAllmap.put(Const.CODE, articles.getCodes());
            titleByOneAllmap.put(Const.IMGURL, articles.getImgUrl());
            titleByOneAllmap.put(Const.CRTIME, DateUtil.getDateShort(articles.getCreate_time()));
        }
        return  titleByOneAllmap;
    }


    /**
     * 热门文章标题列表
     * @return lidasi
     */
    @GetMapping(value = "/hotByList")
    public Map<String, Object> hotByList(){
        Map<String, Object> hotByListMap = new HashMap<String, Object>();
        List<Content> hotByList = new ArrayList<Content>();
        hotByList = contentService.hotByList();
        hotByListMap.put(Const.HOTBYLIST, hotByList);
        return hotByListMap;
    }

    /**
     * 按标题查询热门文章和搜索文章
     * @param request
     * @return lidasi
     * @throws IOException
     */
    @GetMapping(value = "/titleByTwoUrl")
    public Map<String, Object> titleByTwo(HttpServletRequest request) throws IOException {
        String reArray = request.getParameter(Const.TITLE);
        String[] keys = new String[1];
        Object[] values = new Object[1];
        keys[0] = Const.TITLE_CONTENT;
        values[0] = reArray;

        Map<String, Object> titleByTwoAllmap = new HashMap<String, Object>();
        List<Content> titleByTwoList = new ArrayList<Content>();
        titleByTwoList = contentService.titleByOneAll(keys, values);
        for(Content titleList : titleByTwoList) {
            titleByTwoAllmap.put(Const.CONTENT_ID, titleList.getContent_id());
            titleByTwoAllmap.put(Const.TITLE, titleList.getTitle_content());
            titleByTwoAllmap.put(Const.CONTENT, titleList.getContents());
            titleByTwoAllmap.put(Const.CODE, titleList.getCodes());
            titleByTwoAllmap.put(Const.IMGURL, titleList.getImgUrl());
            titleByTwoAllmap.put(Const.CRTIME, DateUtil.getDateShort(titleList.getCreate_time()));
        }
        return  titleByTwoAllmap;
    }
}
