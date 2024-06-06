package org.example.dao;

import org.example.HibernateUtil;
import org.example.model.Souvenir;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SouvenirDAO {

    public void saveSouvenir(Souvenir souvenir) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(souvenir);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateSouvenir(Souvenir souvenir) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(souvenir);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteSouvenir(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Souvenir souvenir = session.get(Souvenir.class, id);
            if (souvenir != null) {
                session.delete(souvenir);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Souvenir getSouvenir(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Souvenir.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Souvenir> getSouvenirsByManufacturer(int manufacturerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Souvenir> query = session.createQuery("FROM Souvenir WHERE manufacturer.id = :manufacturerId", Souvenir.class);
            query.setParameter("manufacturerId", manufacturerId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Souvenir> getSouvenirsByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Souvenir> query = session.createQuery(
                    "SELECT s FROM Souvenir s JOIN s.manufacturer m WHERE m.country = :country", Souvenir.class);
            query.setParameter("country", country);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Souvenir> getAllSouvenirs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Souvenir", Souvenir.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
