CREATE DATABASE IF NOT EXISTS prodDB;
USE prodDB;

CREATE TABLE IF NOT EXISTS Products (
                                        product_id INT AUTO_INCREMENT PRIMARY KEY,
                                        product_name VARCHAR(100) NOT NULL,
                                        product_group VARCHAR(100) NOT NULL,
                                        description TEXT,
                                        release_date DATE
);

CREATE TABLE IF NOT EXISTS Parameters (
                                          param_id INT AUTO_INCREMENT PRIMARY KEY,
                                          product_id INT,
                                          param_name VARCHAR(100) NOT NULL,
                                          param_group VARCHAR(100) NOT NULL,
                                          param_value VARCHAR(100),
                                          unit VARCHAR(50)
);

INSERT INTO Products (product_name, product_group, description, release_date)
VALUES
    ('iPhone 12', 'Телефоны', 'Новейший смартфон Apple', '2020-10-23'),
    ('Samsung TV', 'Телевизоры', 'Телевизор с высоким разрешением', '2020-03-15'),
    ('MacBook Pro', 'Ноутбуки', 'Ноутбук Apple для профессионалов', '2021-05-21'),
    ('Galaxy S21', 'Телефоны', 'Флагманский смартфон Samsung', '2021-01-29');

INSERT INTO Parameters (product_id, param_name, param_group, param_value, unit)
VALUES
    ((SELECT product_id FROM Products WHERE product_name = 'iPhone 12'), 'Вес', 'Физические', '162g', 'грамм'),
    ((SELECT product_id FROM Products WHERE product_name = 'iPhone 12'), 'Процессор', 'Технические', 'A14 Bionic', 'тип'),
    ((SELECT product_id FROM Products WHERE product_name = 'Samsung TV'), 'Диагональ', 'Физические', '55"', 'дюйм'),
    ((SELECT product_id FROM Products WHERE product_name = 'MacBook Pro'), 'Оперативная память', 'Технические', '16GB', 'гигабайт'),
    ((SELECT product_id FROM Products WHERE product_name = 'Galaxy S21'), 'Вес', 'Физические', '169g', 'грамм'),
    ((SELECT product_id FROM Products WHERE product_name = 'Galaxy S21'), 'Процессор', 'Технические', 'Exynos 2100', 'тип');
