package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends AbstractHibernateDAO<Actor>{
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
