package org.example.service;

import org.example.dao.EnglishWordDao;
import org.example.model.EnglishWord;
import org.example.model.RussianWord;
import org.hibernate.SessionFactory;

import java.util.List;

public class EnglishWordService {
    private final EnglishWordDao englishWordDAO;

    public EnglishWordService(SessionFactory sessionFactory) {
        this.englishWordDAO = new EnglishWordDao(sessionFactory);
    }

    public void addOrUpdateEnglishWord(EnglishWord englishWord) {
        englishWordDAO.addOrUpdateEnglishWord(englishWord);
    }

    public EnglishWord getEnglishWordById(int id) {
        return englishWordDAO.getEnglishWordById(id);
    }

    public List<EnglishWord> getEnglishWordByRusWord(String rusWord) {
        return englishWordDAO.getEnglishWordRusWord(rusWord);
    }
    public List<RussianWord> getRusWordsByEndWord(String endWord) {
        return englishWordDAO.getRusWordsByEndWord(endWord);
    }

    public void deleteEnglishWordById(int wordId) {
        englishWordDAO.deleteEnglishWordById(wordId);
    }
}
