package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends AbstractHibernateDAO<Category>{
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
