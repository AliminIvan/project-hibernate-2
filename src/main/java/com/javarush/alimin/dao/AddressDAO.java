package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends AbstractHibernateDAO<Address>{
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
