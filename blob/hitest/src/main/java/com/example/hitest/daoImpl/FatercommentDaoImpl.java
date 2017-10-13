package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.FatercommentDao;
import com.example.hitest.model.Fatercomment;
import org.springframework.stereotype.Repository;

@Repository
public class FatercommentDaoImpl extends ACommonDao<Fatercomment> implements FatercommentDao {

    protected FatercommentDaoImpl(Class<Fatercomment> entityClass) { super(entityClass); }
    public FatercommentDaoImpl(){
        super(Fatercomment.class);
    }
}
