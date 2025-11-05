package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Pharmacy;
import org.example.repository.PharmacyRepository;

import java.util.List;

public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void save(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }

    public Pharmacy findById(Integer id) {
        return pharmacyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Аптека с id '" + id + "' не найдена!")
        );
    }

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public List<Pharmacy> filter(String search) {
        return pharmacyRepository.filter(search);
    }

    public List<Pharmacy> sort(String sort, String comparator) {

        return pharmacyRepository.sort(sort, comparator);
    }

    public void update(Pharmacy pharmacy) {
        if (findById(pharmacy.getId()) != null) {
            pharmacyRepository.update(pharmacy);
        }
    }

    public void delete(Integer id) {
        pharmacyRepository.delete(id);
    }
}
