package org.example;

import org.example.dao.WeatherDao;
import org.example.model.Weather;
import org.example.service.WeatherService;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static WeatherService weatherService;

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        weatherService = new WeatherService(new WeatherDao(sessionFactory));

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayWeatherByRegion();
                case 2 -> displaySnowyDaysWithNegativeTemperature();
                case 3 -> displayWeatherForRegionsByLanguage();
                case 4 -> displayAverageTemperatureForRegionsWithAreaGreaterThan();
                case 5 -> addWeatherRecord();
                case 6 -> updateWeatherRecord();
                case 7 -> deleteWeatherRecord();
                case 8 -> {
                    System.out.println("Выход из программы");
                    System.exit(0);
                }
                default -> System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 8.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Вывести сведения о погоде в заданном регионе");
        System.out.println("2. Вывести даты, когда в заданном регионе шел снег и температура была ниже заданной отрицательной");
        System.out.println("3. Вывести информацию о погоде за прошедшую неделю в регионах, жители которых общаются на заданном языке");
        System.out.println("4. Вывести среднюю температуру за прошедшую неделю в регионах с площадью больше заданной");
        System.out.println("5. Добавить запись о погоде");
        System.out.println("6. Изменить запись о погоде");
        System.out.println("7. Удалить запись о погоде");
        System.out.println("8. Выход");
    }

    private static void displayWeatherByRegion() {
        System.out.println("Введите название региона:");
        String regionName = scanner.nextLine();
        List<Weather> weatherByRegion = weatherService.getWeatherByRegion(regionName);
        weatherByRegion.forEach(weather ->
                System.out.println(weather.getRegion().getName() + ": Температура: " + weather.getTemperature() + " Тип осадков: " + weather.getPrecipitation()));
    }

    private static void displaySnowyDaysWithNegativeTemperature() {
        System.out.println("Введите название региона:");
        String regionNameForSnow = scanner.nextLine();
        System.out.println("Введите температуру:");
        float temperature = scanner.nextFloat();
        List<Weather> snowyDays = weatherService.getSnowyDaysWithNegativeTemperature(regionNameForSnow, temperature);
        snowyDays.forEach(weather ->
                System.out.println(weather.getDate() + " Температура: " + weather.getTemperature()));
    }

    private static void displayWeatherForRegionsByLanguage() {
        System.out.println("Введите язык:");
        String language = scanner.nextLine();
        List<Weather> weatherByLanguage = weatherService.getWeatherForRegionsByLanguage(language);
        weatherByLanguage.forEach(weather ->
                System.out.println(weather.getRegion().getName() + ": Температура: " + weather.getTemperature() + " Тип осадков: " + weather.getPrecipitation()));
    }

    private static void displayAverageTemperatureForRegionsWithAreaGreaterThan() {
        System.out.println("Введите площадь:");
        float area = scanner.nextFloat();
        float averageTemperature = weatherService.getAverageTemperatureForRegionsWithAreaGreaterThan(area);
        System.out.println("Средняя температура за прошлую неделю: " + averageTemperature);
    }

    private static void addWeatherRecord() {
        Weather newWeather = new Weather();
        System.out.println("Введите температуру:");
        newWeather.setTemperature(scanner.nextFloat());
        scanner.nextLine(); // Consume newline
        System.out.println("Введите тип осадков:");
        newWeather.setPrecipitation(scanner.nextLine());
        System.out.println("Введите дату в формате yyyy-MM-dd:");
        String inputDate = scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(inputDate);
            newWeather.setDate(date);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Пожалуйста, введите дату в формате yyyy-MM-dd.");
            return;
        }
        System.out.println("Введите название региона:");
        newWeather.setRegion(weatherService.getWeatherByRegion(scanner.nextLine()).get(0).getRegion());
        weatherService.addWeather(newWeather);
        System.out.println("Запись о погоде добавлена");
    }

    private static void updateWeatherRecord() {
        System.out.println("Введите ID записи о погоде для изменения:");
        Long id = scanner.nextLong();
        Weather existingWeather = weatherService.getWeatherById(id);
        if (existingWeather != null) {
            System.out.println("Введите новую температуру:");
            existingWeather.setTemperature(scanner.nextFloat());
            weatherService.updateWeather(existingWeather);
            System.out.println("Запись о погоде обновлена");
        } else {
            System.out.println("Запись о погоде с таким ID не найдена");
        }
    }

    private static void deleteWeatherRecord() {
        System.out.println("Введите ID записи о погоде для удаления:");
        Long idToDelete = scanner.nextLong();
        Weather weatherToDelete = weatherService.getWeatherById(idToDelete);
        if (weatherToDelete != null) {
            weatherService.deleteWeather(weatherToDelete);
            System.out.println("Запись о погоде удалена");
        } else {
            System.out.println("Запись о погоде с таким ID не найдена");
        }
    }
}
