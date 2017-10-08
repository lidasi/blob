package com.example.hitest.daoImpl;

import com.example.hitest.dao.ACommonDao;
import com.example.hitest.dao.ContentDao;
import com.example.hitest.model.Content;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentDaoImpl extends ACommonDao<Content> implements ContentDao {

    @Autowired
    SessionFactory sessionFactory;

    protected ContentDaoImpl(Class<Content> entityClass) { super(entityClass); }
    public ContentDaoImpl(){
        super(Content.class);
    }

    @Override
    public List<Content> hotByList() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT title_content FROM content GROUP BY content.count  Desc LIMIT 5";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List list = sqlQuery.list();
        return list;
    }
}
