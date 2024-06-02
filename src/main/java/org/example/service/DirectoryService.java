package org.example.service;

import org.example.dao.DirectoryDAO;
import org.example.dao.FileDAO;
import org.example.model.Directory;
import org.example.model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class DirectoryService {
    private final DirectoryDAO directoryDAO;
    private final FileDAO fileDAO;
    private final SessionFactory sessionFactory;

    public DirectoryService(DirectoryDAO directoryDAO,
                            FileDAO fileDAO,
                            SessionFactory sessionFactory) {
        this.directoryDAO = directoryDAO;
        this.fileDAO = fileDAO;
        this.sessionFactory = sessionFactory;
    }

    public void addDirectory(Directory directory) {
        directoryDAO.save(directory);
    }

    public Directory getDirectory(int id) {
        return directoryDAO.findById(id);
    }

    public void updateDirectory(Directory directory) {
        directoryDAO.update(directory);
    }

    public void deleteDirectory(int id) {
        Directory directory = directoryDAO.findById(id);
        if (directory != null) {
            directoryDAO.delete(directory);
        }
    }

    public String getFullPath(int directoryId) {
        String fullPath = "";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Directory directory = session.createQuery("SELECT d FROM Directory d LEFT JOIN FETCH d.parent WHERE d.id = :id", Directory.class)
                    .setParameter("id", directoryId)
                    .uniqueResult();

            if (directory != null) {
                fullPath = buildFullPath(directory);
            }

            transaction.commit();
            return fullPath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get full path for directory with ID: " + directoryId, e);
        }
    }

    private String buildFullPath(Directory directory) {
        if (directory.getParent() != null) {
            return buildFullPath(directory.getParent()) + "/" + directory.getName();
        } else {
            return directory.getName();
        }
    }

    public int countFilesInDirectory(int directoryId) {
        Directory directory = directoryDAO.getDirectoryWithFiles(directoryId);
        if (directory != null) {
            return directory.getFiles().size();
        }
        return 0;
    }



    private int countFiles(Directory directory) {

        int count = directory.getFiles().size();
        for (Directory subdirectory : directory.getSubdirectories()) {
            count += countFiles(subdirectory);
        }
        return count;
    }
    @Transactional
    public int calculateDiskSpace(int directoryId) {
        Directory directory = directoryDAO.getDirectoryWithFiles(directoryId);
        if (directory == null) {
            return 0;
        }
        int size = directory.getFiles().stream().mapToInt(File::getSize).sum();
        for (Directory subdirectory : directory.getSubdirectories()) {
            size += calculateSize(subdirectory);
        }
        return size;
    }

    private int calculateSize(Directory directory) {
        int size = directory.getFiles().stream().mapToInt(File::getSize).sum();
        for (Directory subdirectory : directory.getSubdirectories()) {
            size += calculateSize(subdirectory);
        }
        return size;
    }

    public Set<File> findFilesByMask(int directoryId, String mask) {
        Directory directory = directoryDAO.findById(directoryId);
        if (directory == null) {
            return new HashSet<>();
        }
        return findFiles(directory, mask);
    }

    private Set<File> findFiles(Directory directory, String mask) {
        Set<File> files = new HashSet<>();
        for (File file : directory.getFiles()) {
            if (file.getName().contains(mask)) {
                files.add(file);
            }
        }
        for (Directory subdirectory : directory.getSubdirectories()) {
            files.addAll(findFiles(subdirectory, mask));
        }
        return files;
    }

    public void moveFilesAndSubdirectories(int sourceDirectoryId, int targetDirectoryId) {
        Directory sourceDirectory = directoryDAO.findById(sourceDirectoryId);
        Directory targetDirectory = directoryDAO.findById(targetDirectoryId);

        if (sourceDirectory != null && targetDirectory != null) {
            for (Directory subDir : sourceDirectory.getSubdirectories()) {
                subDir.setParent(targetDirectory);
                directoryDAO.update(subDir);
            }
            for (File file : sourceDirectory.getFiles()) {
                file.setDirectory(targetDirectory);
                fileDAO.update(file);
            }
        }
    }

    public void deleteFilesAndSubdirectories(int directoryId) {
        Directory directory = directoryDAO.findById(directoryId);
        if (directory != null) {
            for (Directory subDir : directory.getSubdirectories()) {
                deleteFilesAndSubdirectories(subDir.getId());
            }
            for (File file : directory.getFiles()) {
                fileDAO.delete(file);
            }
            directoryDAO.delete(directory);
        }
    }
}