package com.javarush.alimin.dao;

import com.javarush.alimin.entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends AbstractHibernateDAO<City>{
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
