CREATE DATABASE dictionary;

USE dictionary;

CREATE TABLE english_words (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               word VARCHAR(255) NOT NULL
);

CREATE TABLE russian_words (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               word VARCHAR(255) NOT NULL
);

CREATE TABLE translations (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              english_id INT,
                              russian_id INT,
                              FOREIGN KEY (english_id) REFERENCES english_words(id),
                              FOREIGN KEY (russian_id) REFERENCES russian_words(id)
);
-- Вставка данных в таблицу english_words
INSERT INTO english_words (word) VALUES
                                     ('apple'),
                                     ('book'),
                                     ('car'),
                                     ('dog'),
                                     ('house');

-- Вставка данных в таблицу russian_words
INSERT INTO russian_words (word) VALUES
                                     ('яблоко'),
                                     ('книга'),
                                     ('машина'),
                                     ('собака'),
                                     ('дом'),
                                     ('автомобиль'),
                                     ('домашний'),
                                     ('пёс'),
                                     ('литература'),
                                     ('жильё');

-- Вставка данных в таблицу translations
INSERT INTO translations (english_id, russian_id) VALUES
                                                      ((SELECT id FROM english_words WHERE word='apple'), (SELECT id FROM russian_words WHERE word='яблоко')),
                                                      ((SELECT id FROM english_words WHERE word='book'), (SELECT id FROM russian_words WHERE word='книга')),
                                                      ((SELECT id FROM english_words WHERE word='book'), (SELECT id FROM russian_words WHERE word='литература')),
                                                      ((SELECT id FROM english_words WHERE word='car'), (SELECT id FROM russian_words WHERE word='машина')),
                                                      ((SELECT id FROM english_words WHERE word='car'), (SELECT id FROM russian_words WHERE word='автомобиль')),
                                                      ((SELECT id FROM english_words WHERE word='dog'), (SELECT id FROM russian_words WHERE word='собака')),
                                                      ((SELECT id FROM english_words WHERE word='dog'), (SELECT id FROM russian_words WHERE word='пёс')),
                                                      ((SELECT id FROM english_words WHERE word='house'), (SELECT id FROM russian_words WHERE word='дом')),
                                                      ((SELECT id FROM english_words WHERE word='house'), (SELECT id FROM russian_words WHERE word='жильё')),
                                                      ((SELECT id FROM english_words WHERE word='house'), (SELECT id FROM russian_words WHERE word='домашний'));