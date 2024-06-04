package org.example.dao;

import org.example.model.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RegionDao {

    private SessionFactory sessionFactory;

    public RegionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Region get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Region.class, id);
        }
    }

    public void save(Region region) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(region);
            tx.commit();
        }
    }

    public void update(Region region) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(region);
            tx.commit();
        }
    }

    public void delete(Region region) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(region);
            tx.commit();
        }
    }
}