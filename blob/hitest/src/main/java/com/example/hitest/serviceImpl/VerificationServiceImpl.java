package com.example.hitest.serviceImpl;

import com.example.hitest.dao.VerificationDao;
import com.example.hitest.model.Verification;
import com.example.hitest.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VerificationServiceImpl implements VerificationService{
    @Autowired
    VerificationDao verificationDao;

    @Override
    public Verification queyEmail(String[] keys, Object[] values) {
        return verificationDao.queryByKeysAndValues(keys, values);
    }

    @Override
    public void save(Verification verification1) {
        verificationDao.save(verification1);
    }

    @Override
    public void update(Verification verification) {
        verificationDao.update(verification);
    }
}
