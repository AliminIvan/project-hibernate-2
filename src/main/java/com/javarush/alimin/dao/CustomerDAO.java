package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends AbstractHibernateDAO<Customer>{
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
