package com.example.hitest.serviceImpl;

import com.example.hitest.dao.ContentDao;
import com.example.hitest.model.Content;
import com.example.hitest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContentServiceImpl implements ContentService{
    @Autowired
    ContentDao contentDao;

    @Override
    public List<Content> listAll(int first, int lenght) {
        return contentDao.listAll(first, lenght);
    }

    @Override
    public List<Content> titleByOneAll(String[] keys, Object[] values) {
        return contentDao.listByKeysAndValues(keys, values);
    }

    @Override
    public List<Content> hotByList() {
        return contentDao.hotByList();
    }

    @Override
    public int listAllCnt() {
        return contentDao.listAllCnt();
    }

    @Override
    public List<Content> titleListAll() {
        return contentDao.listAll();
    }
}
