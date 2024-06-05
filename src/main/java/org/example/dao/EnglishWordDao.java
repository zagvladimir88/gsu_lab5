package org.example.dao;

import org.example.model.EnglishWord;
import org.example.model.RussianWord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EnglishWordDao {
    private final SessionFactory sessionFactory;

    public EnglishWordDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOrUpdateEnglishWord(EnglishWord englishWord) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(englishWord);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EnglishWord getEnglishWordById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EnglishWord.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<EnglishWord> getEnglishWordRusWord(String russianWord) {
        List<EnglishWord> englishWords = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT t.englishWord FROM Translation t JOIN t.russianWord r WHERE r.word = :russianWord");
            query.setParameter("russianWord", russianWord);
            englishWords = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return englishWords;
    }

    public List<RussianWord> getRusWordsByEndWord(String engWord) {
        List<RussianWord> russianWords = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT t.russianWord FROM Translation t JOIN t.englishWord r WHERE r.word = :engWord");
            query.setParameter("engWord", engWord);
            russianWords = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return russianWords;
    }

    public void deleteEnglishWordById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            EnglishWord englishWord = session.get(EnglishWord.class, id);
            if (englishWord != null) {
                session.delete(englishWord);
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