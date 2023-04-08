package com.javarush.alimin.dao;

import com.javarush.alimin.entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends AbstractHibernateDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
