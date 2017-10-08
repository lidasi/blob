package com.example.hitest.serviceImpl;

import com.example.hitest.dao.UserinfoDao;
import com.example.hitest.model.Userinfo;
import com.example.hitest.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserinfoServiceImpl implements UserinfoService{

    @Autowired
    UserinfoDao userinfoDao;

    @Override
    public Userinfo queryUsername(String[] keys, Object[] values) {
        return userinfoDao.queryByKeysAndValues(keys, values);
    }

    @Override
    public void save(Userinfo userinfo1) {
        userinfoDao.save(userinfo1);
    }
}
