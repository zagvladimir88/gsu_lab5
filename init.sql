CREATE DATABASE weather_db;
USE weather_db;

CREATE TABLE inhabitant_types (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(50) NOT NULL,
                                  language VARCHAR(50) NOT NULL
);

CREATE TABLE regions (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         area FLOAT NOT NULL,
                         inhabitant_type_id INT,
                         FOREIGN KEY (inhabitant_type_id) REFERENCES inhabitant_types(id)
);

CREATE TABLE weather (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         region_id INT,
                         date DATE NOT NULL,
                         temperature FLOAT NOT NULL,
                         precipitation VARCHAR(50) NOT NULL,
                         FOREIGN KEY (region_id) REFERENCES regions(id)
);
INSERT INTO inhabitant_types (name, language) VALUES
                                                  ('Белорусы', 'Белорусский'),
                                                  ('Русские', 'Русский'),
                                                  ('Поляки', 'Польский');

INSERT INTO regions (name, area, inhabitant_type_id) VALUES
                                                         ('Минск', 39854.68, 1),
                                                         ('Гомель', 40372.05, 1),
                                                         ('Брест', 32786.94, 2),
                                                         ('Гродно', 25127.91, 3);

INSERT INTO weather (region_id, date, temperature, precipitation) VALUES
                                                                      (1, '2024-06-01', 22.5, 'Дождь'),
                                                                      (1, '2024-06-02', 25.3, 'Солнечно'),
                                                                      (2, '2024-06-01', 20.1, 'Облачно'),
                                                                      (2, '2024-06-02', 21.4, 'Солнечно'),
                                                                      (3, '2024-06-01', -8, 'Снег'),
                                                                      (3, '2024-06-02', -10, 'Снег'),
                                                                      (4, '2024-06-01', 17.0, 'Дождь'),
                                                                      (4, '2024-06-02', 16.5, 'Дождь');