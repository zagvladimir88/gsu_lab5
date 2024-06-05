package org.example.dao;

import org.example.HibernateUtil;
import org.example.model.EnglishWord;
import org.example.model.RussianWord;
import org.example.model.Translation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TranslationDao {

    private final SessionFactory sessionFactory;

    public TranslationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOrUpdateTranslation(Translation translation) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(translation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}