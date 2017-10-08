package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.UserDao;
import com.example.hitest.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends ACommonDao<User> implements UserDao {
    protected UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
    public UserDaoImpl(){
        super(User.class);
    }

}
