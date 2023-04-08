package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends AbstractHibernateDAO<Film>{
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getAvailableForRentFilm() {
        Query<Film> query = getCurrentSession().createQuery("SELECT f FROM Film f " +
                "WHERE f.id NOT IN(SELECT DISTINCT film.id FROM Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
