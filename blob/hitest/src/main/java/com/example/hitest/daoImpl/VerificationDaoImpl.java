package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.VerificationDao;
import com.example.hitest.model.Verification;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationDaoImpl extends ACommonDao<Verification> implements VerificationDao {
    protected VerificationDaoImpl(Class<Verification> entityClass) { super(entityClass); }
    public VerificationDaoImpl(){
        super(Verification.class);
    }
}
