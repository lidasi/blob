package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.UserinfoDao;
import com.example.hitest.model.Userinfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserinfoDaoImpl extends ACommonDao<Userinfo> implements UserinfoDao {

    protected UserinfoDaoImpl(Class<Userinfo> entityClass) { super(entityClass); }
    public UserinfoDaoImpl(){
        super(Userinfo.class);
    }
}
