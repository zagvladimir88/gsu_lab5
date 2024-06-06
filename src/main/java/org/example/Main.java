package org.example;

import org.example.model.Manufacturer;
import org.example.model.Souvenir;
import org.example.service.ManufacturerService;
import org.example.service.SouvenirService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ManufacturerService manufacturerService = new ManufacturerService();
    private static SouvenirService souvenirService = new SouvenirService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить производителя");
            System.out.println("2. Добавить сувенир");
            System.out.println("3. Обновить производителя");
            System.out.println("4. Обновить сувенир");
            System.out.println("5. Удалить производителя");
            System.out.println("6. Удалить сувенир");
            System.out.println("7. Вывести информацию о сувенирах заданного производителя");
            System.out.println("8. Вывести информацию о сувенирах, произведенных в заданной стране");
            System.out.println("9. Вывести информацию о производителях, чьи цены на сувениры меньше заданной");
            System.out.println("10. Вывести информацию о производителях заданного сувенира, произведенного в заданном году");
            System.out.println("11. Вывести всех производителей");
            System.out.println("12. Вывести все сувениры");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addManufacturer();
                    break;
                case 2:
                    addSouvenir();
                    break;
                case 3:
                    updateManufacturer();
                    break;
                case 4:
                    updateSouvenir();
                    break;
                case 5:
                    deleteManufacturer();
                    break;
                case 6:
                    deleteSouvenir();
                    break;
                case 7:
                    getSouvenirsByManufacturer();
                    break;
                case 8:
                    getSouvenirsByCountry();
                    break;
                case 9:
                    getManufacturersBySouvenirPriceLessThan();
                    break;
                case 10:
                    getManufacturersBySouvenirAndYear();
                    break;
                case 11:
                    getAllManufacturers();
                    break;
                case 12:
                    getAllSouvenirs();
                    break;
                case 0:
                    HibernateUtil.shutdown();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void addManufacturer() {
        System.out.println("Введите название производителя:");
        String name = scanner.nextLine();
        System.out.println("Введите страну производителя:");
        String country = scanner.nextLine();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setCountry(country);
        manufacturerService.addManufacturer(manufacturer);
        System.out.println("Производитель добавлен.");
    }

    private static void addSouvenir() {
        System.out.println("Введите название сувенира:");
        String name = scanner.nextLine();
        System.out.println("Введите ID производителя:");
        int manufacturerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Введите дату выпуска (гггг-мм-дд):");
        String releaseDateStr = scanner.nextLine();
        System.out.println("Введите цену:");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        try {
            Date releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateStr);
            Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId);
            if (manufacturer == null) {
                System.out.println("Производитель с таким ID не найден.");
                return;
            }

            Souvenir souvenir = new Souvenir();
            souvenir.setName(name);
            souvenir.setManufacturer(manufacturer);
            souvenir.setReleaseDate(releaseDate);
            souvenir.setPrice(price);
            souvenirService.addSouvenir(souvenir);
            System.out.println("Сувенир добавлен.");
        } catch (ParseException e) {
            System.out.println("Неверный формат даты.");
        }
    }

    private static void updateManufacturer() {
        System.out.println("Введите ID производителя для обновления:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Manufacturer manufacturer = manufacturerService.getManufacturer(id);
        if (manufacturer == null) {
            System.out.println("Производитель с таким ID не найден.");
            return;
        }

        System.out.println("Введите новое название производителя:");
        String name = scanner.nextLine();
        System.out.println("Введите новую страну производителя:");
        String country = scanner.nextLine();

        manufacturer.setName(name);
        manufacturer.setCountry(country);
        manufacturerService.updateManufacturer(manufacturer);
        System.out.println("Производитель обновлен.");
    }

    private static void updateSouvenir() {
        System.out.println("Введите ID сувенира для обновления:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Souvenir souvenir = souvenirService.getSouvenir(id);
        if (souvenir == null) {
            System.out.println("Сувенир с таким ID не найден.");
            return;
        }

        System.out.println("Введите новое название сувенира:");
        String name = scanner.nextLine();
        System.out.println("Введите новую дату выпуска (гггг-мм-дд):");
        String releaseDateStr = scanner.nextLine();
        System.out.println("Введите новую цену:");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        try {
            Date releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateStr);
            souvenir.setName(name);
            souvenir.setReleaseDate(releaseDate);
            souvenir.setPrice(price);
            souvenirService.updateSouvenir(souvenir);
            System.out.println("Сувенир обновлен.");
        } catch (ParseException e) {
            System.out.println("Неверный формат даты.");
        }
    }

    private static void deleteManufacturer() {
        System.out.println("Введите ID производителя для удаления:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        manufacturerService.deleteManufacturer(id);
        System.out.println("Производитель и его сувениры удалены.");
    }

    private static void deleteSouvenir() {
        System.out.println("Введите ID сувенира для удаления:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        souvenirService.deleteSouvenir(id);
        System.out.println("Сувенир удален.");
    }

    private static void getSouvenirsByManufacturer() {
        System.out.println("Введите ID производителя:");
        int manufacturerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Souvenir> souvenirs = souvenirService.getSouvenirsByManufacturer(manufacturerId);
        if (souvenirs == null || souvenirs.isEmpty()) {
            System.out.println("Сувениры не найдены.");
            return;
        }

        souvenirs.forEach(souvenir -> System.out.println("ID: " + souvenir.getId() + ", Название: " + souvenir.getName() +
                ", Дата выпуска: " + souvenir.getReleaseDate() + ", Цена: " + souvenir.getPrice()));
    }

    private static void getSouvenirsByCountry() {
        System.out.println("Введите страну:");
        String country = scanner.nextLine();

        List<Souvenir> souvenirs = souvenirService.getSouvenirsByCountry(country);
        if (souvenirs == null || souvenirs.isEmpty()) {
            System.out.println("Сувениры не найдены.");
            return;
        }

        souvenirs.forEach(souvenir -> System.out.println("ID: " + souvenir.getId() + ", Название: " + souvenir.getName() +
                ", Дата выпуска: " + souvenir.getReleaseDate() + ", Цена: " + souvenir.getPrice()));
    }

    private static void getManufacturersBySouvenirPriceLessThan() {
        System.out.println("Введите цену:");
        double priceInput = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Преобразуем значение double в BigDecimal
        BigDecimal price = BigDecimal.valueOf(priceInput);

        List<Manufacturer> manufacturers = manufacturerService.getManufacturersBySouvenirPriceLessThan(price);
        if (manufacturers == null || manufacturers.isEmpty()) {
            System.out.println("Производители не найдены.");
            return;
        }

        manufacturers.forEach(manufacturer -> System.out.println("ID: " + manufacturer.getId() + ", Название: " +
                manufacturer.getName() + ", Страна: " + manufacturer.getCountry()));
    }


    private static void getManufacturersBySouvenirAndYear() {
        System.out.println("Введите название сувенира:");
        String souvenirName = scanner.nextLine();
        System.out.println("Введите год выпуска:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Manufacturer> manufacturers = manufacturerService.getManufacturersBySouvenirAndYear(souvenirName, year);
        if (manufacturers == null || manufacturers.isEmpty()) {
            System.out.println("Производители не найдены.");
            return;
        }

        manufacturers.forEach(manufacturer -> System.out.println("ID: " + manufacturer.getId() + ", Название: " +
                manufacturer.getName() + ", Страна: " + manufacturer.getCountry()));
    }

    private static void getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        if (manufacturers == null || manufacturers.isEmpty()) {
            System.out.println("Производители не найдены.");
            return;
        }

        manufacturers.forEach(manufacturer -> System.out.println("ID: " + manufacturer.getId() + ", Название: " +
                manufacturer.getName() + ", Страна: " + manufacturer.getCountry()));
    }

    private static void getAllSouvenirs() {
        List<Souvenir> souvenirs = souvenirService.getAllSouvenirs();
        if (souvenirs == null || souvenirs.isEmpty()) {
            System.out.println("Сувениры не найдены.");
            return;
        }

        souvenirs.forEach(souvenir -> System.out.println("ID: " + souvenir.getId() + ", Название: " + souvenir.getName() +
                ", Дата выпуска: " + souvenir.getReleaseDate() + ", Цена: " + souvenir.getPrice()));
    }
}
