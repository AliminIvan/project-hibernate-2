package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractHibernateDAO<Rental>{
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getRandomUnreturnedRental() {
        Query<Rental> query = getCurrentSession().createQuery("SELECT r FROM Rental r WHERE r.returnDate IS NULL", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
