package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Medicine;
import org.example.repository.MedicineRepository;

import java.util.List;

public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final ProducerService producerService;

    public MedicineService(MedicineRepository medicineRepository,
                           ProducerService producerService) {
        this.medicineRepository = medicineRepository;
        this.producerService = producerService;
    }

    public void save(Medicine medicine) {
        checkForeignKey(medicine.getProducerId());
        medicineRepository.save(medicine);
    }

    public Medicine findById(Integer id) {
        return medicineRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Лекарство с id '" + id + "' не найдено!")
        );
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public void update(Medicine medicine) {
        findById(medicine.getId());
        checkForeignKey(medicine.getProducerId());
        medicineRepository.update(medicine);
    }

    public void delete(Integer id) {
        medicineRepository.delete(id);
    }

    private void checkForeignKey(Integer producerId) {
        producerService.findById(producerId);
    }
}
