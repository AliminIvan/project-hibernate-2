package com.javarush.alimin.dao;

import com.javarush.alimin.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends AbstractHibernateDAO<FilmText>{
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
