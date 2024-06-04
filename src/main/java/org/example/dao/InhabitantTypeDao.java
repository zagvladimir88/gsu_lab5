package org.example.dao;

import org.example.model.InhabitantType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InhabitantTypeDao {

    private SessionFactory sessionFactory;

    public InhabitantTypeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public InhabitantType get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InhabitantType.class, id);
        }
    }

    public void save(InhabitantType inhabitantType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(inhabitantType);
            tx.commit();
        }
    }

    public void update(InhabitantType inhabitantType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(inhabitantType);
            tx.commit();
        }
    }

    public void delete(InhabitantType inhabitantType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(inhabitantType);
            tx.commit();
        }
    }
}