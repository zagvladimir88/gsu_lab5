package org.example.dao;

import org.example.model.Directory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class DirectoryDAO {
    private final SessionFactory sessionFactory;

    public DirectoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Directory directory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(directory);
        transaction.commit();
        session.close();
    }

    public Directory findById(int id) {
        Session session = sessionFactory.openSession();
        Directory directory = session.get(Directory.class, id);
        session.close();
        return directory;
    }

    public void update(Directory directory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(directory);
        transaction.commit();
        session.close();
    }

    public void delete(Directory directory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(directory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Directory> findAll() {
        Session session = sessionFactory.openSession();
        List<Directory> directories = session.createQuery("from Directory").list();
        session.close();
        return directories;
    }
    @Transactional
    public Directory getDirectoryWithFiles(int id) {
        Session session = sessionFactory.openSession();
        Query<Directory> query = session.createQuery(
                "SELECT d FROM Directory d " +
                        "LEFT JOIN FETCH d.files " +
                        "LEFT JOIN FETCH d.subdirectories s " +
                        "LEFT JOIN FETCH s.files " +
                        "LEFT JOIN FETCH s.subdirectories ss " +
                        "LEFT JOIN FETCH ss.files " +
                        "WHERE d.id = :id", Directory.class);
        query.setParameter("id", id);
        Directory directory = query.uniqueResult();
        session.close();
        return directory;
    }

    public void deleteDirectoryWithNativeQuery(int directoryId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Delete files in the directory
            session.createNativeQuery("DELETE FROM files WHERE directory_id = :directoryId")
                    .setParameter("directoryId", directoryId)
                    .executeUpdate();

            // Delete subdirectories and their contents recursively
            deleteSubdirectories(session, directoryId);

            // Delete the directory itself
            session.createNativeQuery("DELETE FROM directories WHERE id = :directoryId")
                    .setParameter("directoryId", directoryId)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private void deleteSubdirectories(Session session, int parentDirectoryId) {
        // Get subdirectory IDs
        List<Integer> subdirectoryIds = session.createNativeQuery("SELECT id FROM directories WHERE parent_id = :parentDirectoryId")
                .setParameter("parentDirectoryId", parentDirectoryId)
                .list();

        for (Integer subdirectoryId : subdirectoryIds) {
            // Delete files in the subdirectory
            session.createNativeQuery("DELETE FROM files WHERE directory_id = :subdirectoryId")
                    .setParameter("subdirectoryId", subdirectoryId)
                    .executeUpdate();

            // Recursively delete subdirectories and their contents
            deleteSubdirectories(session, subdirectoryId);

            // Delete the subdirectory itself
            session.createNativeQuery("DELETE FROM directories WHERE id = :subdirectoryId")
                    .setParameter("subdirectoryId", subdirectoryId)
                    .executeUpdate();
        }
    }
}
