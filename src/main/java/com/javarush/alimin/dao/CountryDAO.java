package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends AbstractHibernateDAO<Country>{
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
