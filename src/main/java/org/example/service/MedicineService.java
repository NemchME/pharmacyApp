package org.example.service;

import org.example.model.Medicine;
import org.example.repository.MedicineRepository;

import java.util.List;

public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void save(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public Medicine findById(Integer id) {
        return medicineRepository.findById(id).orElse(null);
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public void update(Medicine medicine) {
        medicineRepository.update(medicine);
    }

    public void delete(Integer id) {
        medicineRepository.delete(id);
    }
}
