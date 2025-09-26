package org.example.service;

import org.example.model.AvailabilityOfMedicine;
import org.example.repository.AvailabilityOfMedicineRepository;

import java.util.List;

public class AvailabilityOfMedicineService {

    private final AvailabilityOfMedicineRepository availabilityOfMedicineRepository;

    public AvailabilityOfMedicineService(AvailabilityOfMedicineRepository availabilityOfMedicineRepository) {
        this.availabilityOfMedicineRepository = availabilityOfMedicineRepository;
    }

    public void save(AvailabilityOfMedicine availabilityOfMedicine) {
        availabilityOfMedicineRepository.save(availabilityOfMedicine);
    }

    public AvailabilityOfMedicine findById(Integer id) {
        return availabilityOfMedicineRepository.findById(id).orElse(null);
    }

    public List<AvailabilityOfMedicine> findAll() {
        return availabilityOfMedicineRepository.findAll();
    }

    public void update(AvailabilityOfMedicine availabilityOfMedicine) {
        availabilityOfMedicineRepository.update(availabilityOfMedicine);
    }

    public void delete(Integer id) {
        availabilityOfMedicineRepository.delete(id);
    }
}
