package com.example.hitest.serviceImpl;

import com.example.hitest.dao.UserDao;
import com.example.hitest.model.User;
import com.example.hitest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUser() {
        return userDao.listAll();
    }
}
