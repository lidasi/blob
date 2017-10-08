package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.TitleDao;
import com.example.hitest.model.Title;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TitleDaoImpl extends ACommonDao<Title> implements TitleDao {
    protected TitleDaoImpl(Class<Title> entityClass) { super(entityClass); }
    public TitleDaoImpl(){
        super(Title.class);
    }
}
