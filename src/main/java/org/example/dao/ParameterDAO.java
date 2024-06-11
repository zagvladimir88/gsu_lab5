package org.example.dao;

import org.example.model.Parameters;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ParameterDAO {

    private Session session;

    public ParameterDAO(Session session) {
        this.session = session;
    }

    public void saveParameter(Parameters parameter) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(parameter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Parameters getParameterById(Long paramId) {
        return session.get(Parameters.class, paramId);
    }

    public List<Parameters> getAllParameters() {
        return session.createQuery("FROM Parameters", Parameters.class).list();
    }

    public void updateParameter(Parameters parameter) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(parameter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteParameter(Parameters parameter) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(parameter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
