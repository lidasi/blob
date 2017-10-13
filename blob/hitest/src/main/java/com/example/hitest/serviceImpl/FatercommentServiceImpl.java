package com.example.hitest.serviceImpl;

import com.example.hitest.dao.FatercommentDao;
import com.example.hitest.model.Fatercomment;
import com.example.hitest.service.FatercommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FatercommentServiceImpl implements FatercommentService {

    @Autowired
    FatercommentDao fatercommentDao;

    @Override
    public List<Fatercomment> commentQuery(String[] keys, Object[] values) {
       return  fatercommentDao.listByKeysAndValues(keys, values);
    }

    @Override
    public Fatercomment byOneQuery(String[] keys, Object[] values) {
        return fatercommentDao.queryByKeysAndValues(keys, values);
    }
}
