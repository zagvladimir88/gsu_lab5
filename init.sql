-- Создание базы данных
CREATE DATABASE IF NOT EXISTS FileSystem;
USE FileSystem;

-- Создание таблицы для каталогов
CREATE TABLE IF NOT EXISTS directories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT DEFAULT NULL,
    name VARCHAR(255) NOT NULL
);

-- Создание таблицы для файлов
CREATE TABLE IF NOT EXISTS files (
    id INT AUTO_INCREMENT PRIMARY KEY,
    directory_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    size INT NOT NULL
);

-- Наполнение таблицы directories тестовыми данными
INSERT INTO directories (parent_id, name) VALUES
(NULL, 'root'),
(1, 'home'),
(1, 'var'),
(2, 'user1'),
(2, 'user2'),
(3, 'log'),
(3, 'tmp'),
(4, 'docs'),
(4, 'pictures'),
(5, 'downloads');

-- Наполнение таблицы files тестовыми данными
INSERT INTO files (directory_id, name, size) VALUES
(2, 'file1.txt', 2048),
(2, 'file2.txt', 1024),
(4, 'file3.txt', 512),
(5, 'file4.txt', 256),
(7, 'file5.log', 4096),
(8, 'file6.tmp', 8192),
(9, 'file7.doc', 1024),
(10, 'file8.jpg', 2048),
(10, 'file9.png', 1024),
(11, 'file10.zip', 512);