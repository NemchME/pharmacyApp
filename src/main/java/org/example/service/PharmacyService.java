package org.example.service;

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
        return pharmacyRepository.findById(id).orElse(null);
    }

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public void update(Pharmacy pharmacy) {
        pharmacyRepository.update(pharmacy);
    }

    public void delete(Integer id) {
        pharmacyRepository.delete(id);
    }
}
