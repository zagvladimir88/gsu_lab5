package org.example.service;

import org.example.dao.WeatherDao;
import org.example.model.Weather;

import java.util.Date;
import java.util.List;

public class WeatherService {

    private final WeatherDao weatherDao;

    public WeatherService(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public List<Weather> getWeatherByRegion(String regionName) {
        return weatherDao.getWeatherByRegion(regionName);
    }

    public List<Weather> getSnowyDaysWithNegativeTemperature(String regionName, float temperature) {
        return weatherDao.getSnowyDaysWithNegativeTemperature(regionName, temperature);
    }

    public List<Weather> getWeatherForRegionsByLanguage(String language) {
        return weatherDao.getWeatherForRegionsByLanguage(language);
    }

    public float getAverageTemperatureForRegionsWithAreaGreaterThan(float area) {
        return weatherDao.getAverageTemperatureForRegionsWithAreaGreaterThan(area);
    }


    public void addWeather(Weather weather) {
        weatherDao.save(weather);
    }

    public void updateWeather(Weather weather) {
        weatherDao.update(weather);
    }

    public void deleteWeather(Weather weather) {
        weatherDao.delete(weather);
    }

    public Weather getWeatherById(Long id) {
        return weatherDao.get(id);
    }
}
