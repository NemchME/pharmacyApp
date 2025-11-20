package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Medicine;
import org.example.model.Pharmacy;
import org.example.model.Producer;
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
        if (checkForeignKey(medicine.getProducerId())) {
            medicineRepository.save(medicine);
        }
    }

    public Medicine findById(Integer id) {
        return medicineRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Лекарство с id '" + id + "' не найдено!")
        );
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public List<Medicine> findAll(int page, int size) {
        return medicineRepository.findAll(page, size);
    }

    public int getTotalPages(int size) {
        int total = medicineRepository.countAll();
        return (int) Math.ceil((double) total / size);
    }

    public List<Medicine> filter(String search) {
        return medicineRepository.filter(search);
    }

    public List<Medicine> sort(String sort, String comparator) {

        return medicineRepository.sort(sort, comparator);
    }

    public void update(Medicine medicine) {
        if (findById(medicine.getId()) != null && checkForeignKey(medicine.getProducerId())) {
            medicineRepository.update(medicine);
        }
    }

    public void delete(Integer id) {
        medicineRepository.delete(id);
    }

    private boolean checkForeignKey(Integer producerId) {
        return producerService.findById(producerId) != null;
    }

    public ProducerService getProducerService() {
        return producerService;
    }

}
