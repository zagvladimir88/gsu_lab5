package org.example.dao;

import org.example.model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class WeatherDao {

    private final SessionFactory sessionFactory;

    public WeatherDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Weather get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Weather.class, id);
        }
    }

    public void save(Weather weather) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(weather);
            tx.commit();
        }
    }

    public void update(Weather weather) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(weather);
            tx.commit();
        }
    }

    public void delete(Weather weather) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(weather);
            tx.commit();
        }
    }

    public List<Weather> getWeatherByRegion(String regionName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Weather> query = session.createQuery("FROM Weather WHERE region.name = :regionName", Weather.class);
            query.setParameter("regionName", regionName);
            return query.list();
        }
    }

    public List<Weather> getSnowyDaysWithNegativeTemperature(String regionName, float temperature) {
        try (Session session = sessionFactory.openSession()) {
            Query<Weather> query = session.createQuery("FROM Weather WHERE region.name = :regionName" +
                    " AND temperature < :temperature AND precipitation = 'Снег'", Weather.class);
            query.setParameter("regionName", regionName);
            query.setParameter("temperature", temperature);
            return query.list();
        }
    }

    public List<Weather> getWeatherForRegionsByLanguage(String language) {
        try (Session session = sessionFactory.openSession()) {
            Query<Weather> query = session.createQuery("FROM Weather WHERE region.inhabitantType.language = :language", Weather.class);
            query.setParameter("language", language);
            return query.list();
        }
    }

    public float getAverageTemperatureForRegionsWithAreaGreaterThan(float area) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery("SELECT AVG(temperature) FROM Weather WHERE region.area > :area", Double.class);
            query.setParameter("area", area);
            return query.uniqueResult().floatValue();
        }
    }
}