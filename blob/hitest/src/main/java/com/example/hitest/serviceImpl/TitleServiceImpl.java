package com.example.hitest.serviceImpl;

import com.example.hitest.dao.TitleDao;
import com.example.hitest.model.Title;
import com.example.hitest.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TitleServiceImpl implements TitleService{

    @Autowired
    TitleDao titleDao;

    @Override
    public List<Title> titleListAll() {

        return titleDao.listAll();
    }
}
