package com.example.hitest.dao;

import com.example.hitest.model.Content;

import java.util.List;

public interface ContentDao extends ICommonDao<Content> {
    List<Content> hotByList();
}
