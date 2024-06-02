package org.example.dao;

import org.example.model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FileDAO {
    private final SessionFactory sessionFactory;

    public FileDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(File file) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(file);
        transaction.commit();
        session.close();
    }

    public File findById(int id) {
        Session session = sessionFactory.openSession();
        File file = session.get(File.class, id);
        session.close();
        return file;
    }

    public void update(File file) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(file);
        transaction.commit();
        session.close();
    }

    public void delete(File file) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(file);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<File> findAll() {
        Session session = sessionFactory.openSession();
        List<File> files = session.createQuery("from File").list();
        session.close();
        return files;
    }

    @SuppressWarnings("unchecked")
    public List<File> findByMask(String mask) {
        Session session = sessionFactory.openSession();
        List<File> files = session.createQuery("from File where name like :mask")
                .setParameter("mask", mask)
                .list();
        session.close();
        return files;
    }
}