package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends AbstractHibernateDAO<Payment>{
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
