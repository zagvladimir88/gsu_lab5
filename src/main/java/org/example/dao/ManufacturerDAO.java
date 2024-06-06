package org.example.dao;

import org.example.HibernateUtil;
import org.example.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class ManufacturerDAO {

    public void saveManufacturer(Manufacturer manufacturer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteManufacturer(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacturer manufacturer = session.get(Manufacturer.class, id);
            if (manufacturer != null) {
                session.delete(manufacturer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Manufacturer getManufacturer(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Manufacturer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Manufacturer> getManufacturersByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Manufacturer> query = session.createQuery("FROM Manufacturer WHERE country = :country", Manufacturer.class);
            query.setParameter("country", country);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Manufacturer> getManufacturersBySouvenirPriceLessThan(BigDecimal price) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Manufacturer> query = session.createQuery(
                    "SELECT DISTINCT m FROM Manufacturer m JOIN m.souvenirs s WHERE s.price < :price", Manufacturer.class);
            query.setParameter("price", price);
            return query.getResultList();
        }
    }


    public List<Manufacturer> getManufacturersBySouvenirAndYear(String souvenirName, int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Manufacturer> query = session.createQuery(
                    "SELECT m FROM Manufacturer m JOIN m.souvenirs s WHERE s.name = :name AND year(s.releaseDate) = :year",
                    Manufacturer.class);
            query.setParameter("name", souvenirName);
            query.setParameter("year", year);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Manufacturer> getAllManufacturers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Manufacturer", Manufacturer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
