package com.example.hitest.service;

import com.example.hitest.model.Content;

import java.util.List;

public interface ContentService {

    List<Content> listAll(int first, int lenght);

    List<Content> titleByOneAll(String[] keys, Object[] values);

    List<Content> hotByList();

    int listAllCnt();

    List<Content> titleListAll();
}
