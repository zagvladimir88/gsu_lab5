package org.example.service;

import org.example.dao.FileDAO;
import org.example.model.File;

public class FileService {
    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void addFile(File file) {
        fileDAO.save(file);
    }

    public File getFile(int id) {
        return fileDAO.findById(id);
    }

    public void updateFile(File file) {
        fileDAO.update(file);
    }

    public void deleteFile(int id) {
        File file = fileDAO.findById(id);
        if (file != null) {
            fileDAO.delete(file);
        }
    }
}