package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends AbstractHibernateDAO<Staff>{
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
