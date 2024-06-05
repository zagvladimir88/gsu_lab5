package org.example.service;

import org.example.dao.TranslationDao;
import org.example.model.Translation;
import org.hibernate.SessionFactory;

import java.util.List;

public class TranslationService {
    private final TranslationDao translationDAO;

    public TranslationService(SessionFactory sessionFactory) {
        this.translationDAO = new TranslationDao(sessionFactory);
    }

    public void addOrUpdateTranslation(Translation translation) {
        translationDAO.addOrUpdateTranslation(translation);
    }

}
