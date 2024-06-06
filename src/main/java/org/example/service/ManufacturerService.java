package org.example.service;

import org.example.dao.ManufacturerDAO;
import org.example.model.Manufacturer;

import java.math.BigDecimal;
import java.util.List;

public class ManufacturerService {
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAO();

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.saveManufacturer(manufacturer);
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.updateManufacturer(manufacturer);
    }

    public void deleteManufacturer(int id) {
        manufacturerDAO.deleteManufacturer(id);
    }

    public Manufacturer getManufacturer(int id) {
        return manufacturerDAO.getManufacturer(id);
    }

    public List<Manufacturer> getManufacturersByCountry(String country) {
        return manufacturerDAO.getManufacturersByCountry(country);
    }

    public List<Manufacturer> getManufacturersBySouvenirPriceLessThan(BigDecimal price) {
        return manufacturerDAO.getManufacturersBySouvenirPriceLessThan(price);
    }

    public List<Manufacturer> getManufacturersBySouvenirAndYear(String souvenirName, int year) {
        return manufacturerDAO.getManufacturersBySouvenirAndYear(souvenirName, year);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDAO.getAllManufacturers();
    }
}
