package com.example.hitest.controller;

import com.example.hitest.model.Content;
import com.example.hitest.model.Title;
import com.example.hitest.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class TitleController {
    @Autowired
    TitleService titleService;

    @GetMapping(value = "/titleListAll")
    public List<String> titleListAll(){
        List<String> titleAll = new ArrayList<String>();
        List<Title> titlelist = titleService.titleListAll();
        for (Title title : titlelist) {
            titleAll.add(title.getTitle_content());
        }
        return titleAll;

    }
}
