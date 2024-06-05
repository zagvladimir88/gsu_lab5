package org.example.service;

import org.example.dao.RussianWordDao;
import org.example.model.RussianWord;
import org.hibernate.SessionFactory;

public class RussianWordService {
    private final RussianWordDao russianWordDAO;

    public RussianWordService(SessionFactory sessionFactory) {
        this.russianWordDAO = new RussianWordDao(sessionFactory);
    }

    public void addOrUpdateRussianWord(RussianWord russianWord) {
        russianWordDAO.addOrUpdateRussianWord(russianWord);
    }

    public RussianWord getRussianWordById(int id) {
        return russianWordDAO.getRussianWordById(id);
    }

    public void deleteRussianWordById(int wordId) {
        russianWordDAO.deleteRussianWordById(wordId);
    }

}
