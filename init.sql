CREATE DATABASE IF NOT EXISTS SouvenirDB;
USE SouvenirDB;

CREATE TABLE Manufacturers (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               country VARCHAR(255) NOT NULL
);

CREATE TABLE Souvenirs (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           manufacturer_id INT,
                           release_date DATE,
                           price DECIMAL(10, 2),
                           FOREIGN KEY (manufacturer_id) REFERENCES Manufacturers(id)
);
INSERT INTO Manufacturers (name, country) VALUES
                                              ('Producer1', 'USA'),
                                              ('Producer2', 'France'),
                                              ('Producer3', 'Germany'),
                                              ('Producer4', 'Italy'),
                                              ('Producer5', 'Spain'),
                                              ('Producer6', 'Japan'),
                                              ('Producer7', 'China'),
                                              ('Producer8', 'Russia'),
                                              ('Producer9', 'India'),
                                              ('Producer10', 'Brazil');

INSERT INTO Souvenirs (name, manufacturer_id, release_date, price) VALUES
                                                                       ('Souvenir4', 1, '2023-01-01', 10.00),
                                                                       ('Souvenir2', 2, '2022-02-01', 15.50),
                                                                       ('Souvenir3', 3, '2021-03-01', 20.00),
                                                                       ('Souvenir4', 4, '2023-04-01', 25.75),
                                                                       ('Souvenir5', 5, '2020-05-01', 30.00),
                                                                       ('Souvenir6', 6, '2023-06-01', 35.50),
                                                                       ('Souvenir7', 7, '2019-07-01', 40.00),
                                                                       ('Souvenir8', 8, '2023-08-01', 45.25),
                                                                       ('Souvenir9', 9, '2021-09-01', 50.00),
                                                                       ('Souvenir10', 10, '2023-10-01', 55.75);
