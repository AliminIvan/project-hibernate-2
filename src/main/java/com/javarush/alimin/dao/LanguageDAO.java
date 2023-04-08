package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends AbstractHibernateDAO<Language>{
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
