package org.example.service;

import org.example.dao.SouvenirDAO;
import org.example.model.Souvenir;

import java.util.List;

public class SouvenirService {
    private SouvenirDAO souvenirDAO = new SouvenirDAO();

    public void addSouvenir(Souvenir souvenir) {
        souvenirDAO.saveSouvenir(souvenir);
    }

    public void updateSouvenir(Souvenir souvenir) {
        souvenirDAO.updateSouvenir(souvenir);
    }

    public void deleteSouvenir(int id) {
        souvenirDAO.deleteSouvenir(id);
    }

    public Souvenir getSouvenir(int id) {
        return souvenirDAO.getSouvenir(id);
    }

    public List<Souvenir> getSouvenirsByManufacturer(int manufacturerId) {
        return souvenirDAO.getSouvenirsByManufacturer(manufacturerId);
    }

    public List<Souvenir> getSouvenirsByCountry(String country) {
        return souvenirDAO.getSouvenirsByCountry(country);
    }

    public List<Souvenir> getAllSouvenirs() {
        return souvenirDAO.getAllSouvenirs();
    }

}
