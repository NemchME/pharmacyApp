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

    public void update(Pharmacy pharmacy) {
        findById(pharmacy.getId());
        pharmacyRepository.update(pharmacy);
    }

    public void delete(Integer id) {
        pharmacyRepository.delete(id);
    }
}
