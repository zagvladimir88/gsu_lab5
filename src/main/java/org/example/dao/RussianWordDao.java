package org.example.dao;

import org.example.model.RussianWord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RussianWordDao {
    private final SessionFactory sessionFactory;

    public RussianWordDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOrUpdateRussianWord(RussianWord russianWord) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(russianWord);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public RussianWord getRussianWordById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(RussianWord.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRussianWordById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            RussianWord russianWord = session.get(RussianWord.class, id);
            if (russianWord != null) {
                session.delete(russianWord);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}