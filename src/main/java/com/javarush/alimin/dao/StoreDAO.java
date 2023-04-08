package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends AbstractHibernateDAO<Store>{
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
