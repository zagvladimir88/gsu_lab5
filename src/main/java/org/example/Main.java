package org.example;

import org.example.model.EnglishWord;
import org.example.model.RussianWord;
import org.example.model.Translation;
import org.example.service.EnglishWordService;
import org.example.service.RussianWordService;
import org.example.service.TranslationService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Инициализация и получение сервисов и фабрики сессий Hibernate
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EnglishWordService englishWordService = new EnglishWordService(sessionFactory);
        RussianWordService russianWordService = new RussianWordService(sessionFactory);
        TranslationService translationService = new TranslationService(sessionFactory);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить слово и перевод");
            System.out.println("2. Удалить слово и перевод");
            System.out.println("3. Обновить слово и перевод");
            System.out.println("4. Вывести все переводы для русских слов");
            System.out.println("5. Вывести все переводы для английских слов");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите слово на английском:");
                    String englishWord = scanner.nextLine();

                    System.out.println("Введите слово на русском:");
                    String russianWord = scanner.nextLine();

                    EnglishWord engWord = new EnglishWord();
                    engWord.setWord(englishWord);
                    englishWordService.addOrUpdateEnglishWord(engWord);

                    RussianWord rusWord = new RussianWord();
                    rusWord.setWord(russianWord);
                    russianWordService.addOrUpdateRussianWord(rusWord);

                    Translation translation = new Translation();
                    translation.setEnglishWord(engWord);
                    translation.setRussianWord(rusWord);
                    translationService.addOrUpdateTranslation(translation);

                    System.out.println("Слово и перевод добавлены успешно.");
                    break;
                case 2:
                    System.out.println("Введите id слова на английском или русском:");
                    int wordId = scanner.nextInt();
                    scanner.nextLine();

                    russianWordService.deleteRussianWordById(wordId);
                    englishWordService.deleteEnglishWordById(wordId);

                    System.out.println("Слово и перевод удалены успешно.");
                    break;
                case 3:
                    System.out.println("Введите id слова на английском или русском:");
                    int wordIdToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    EnglishWord existingEngWord = englishWordService.getEnglishWordById(wordIdToUpdate);
                    RussianWord existingRusWord = russianWordService.getRussianWordById(wordIdToUpdate);

                    if (existingEngWord != null && existingRusWord != null) {
                        System.out.println("Введите новое слово на английском:");
                        String updatedEnglishWord = scanner.nextLine();

                        System.out.println("Введите новое слово на русском:");
                        String updatedRussianWord = scanner.nextLine();

                        // Обновление слова на английском
                        existingEngWord.setWord(updatedEnglishWord);
                        englishWordService.addOrUpdateEnglishWord(existingEngWord);

                        // Обновление слова на русском
                        existingRusWord.setWord(updatedRussianWord);
                        russianWordService.addOrUpdateRussianWord(existingRusWord);

                        System.out.println("Слово и перевод обновлены успешно.");
                    } else {
                        System.out.println("Слово или перевод с указанным id не найдены.");
                    }
                    break;
                case 4:
                    // Вывод всех переводов для русских слов
                    System.out.println("Введите слова через пробел:");
                    String[] words = scanner.nextLine().split("\\s+");
                    for (String word : words) {
                        List<EnglishWord> englishWordByRusWord = englishWordService.getEnglishWordByRusWord(word);
                        if (!englishWordByRusWord.isEmpty()) {
                            System.out.println("Переводы для слова \"" + word + "\":");
                            for (EnglishWord translate : englishWordByRusWord) {
                                System.out.println(translate.getWord());
                            }
                        } else {
                            System.out.println("Слово с id " + word + " не найдено.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Введите слова через пробел:");
                    String[] engWords = scanner.nextLine().split("\\s+");
                    for (String engWordForTranslate : engWords) {
                        List<RussianWord> russianWords = englishWordService.getRusWordsByEndWord(engWordForTranslate);
                        if (!russianWords.isEmpty()) {
                            System.out.println("Переводы для слова \"" + engWordForTranslate + "\":");
                            for (RussianWord translate : russianWords) {
                                System.out.println(translate.getWord());
                            }
                        } else {
                            System.out.println("Слово с id " + engWordForTranslate + " не найдено.");
                        }
                    }
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз.");
            }

            System.out.println("Продолжить? (да/нет)");
            String cont = scanner.nextLine();
            if (!cont.equalsIgnoreCase("да")) {
                exit = true;
            }
        }

        sessionFactory.close();
    }
}

