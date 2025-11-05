package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.AvailabilityOfMedicine;
import org.example.model.Medicine;
import org.example.repository.AvailabilityOfMedicineRepository;

import java.util.List;

public class AvailabilityOfMedicineService {

    private final AvailabilityOfMedicineRepository availabilityOfMedicineRepository;
    private final PharmacyService pharmacyService;
    private final MedicineService medicineService;

    public AvailabilityOfMedicineService(AvailabilityOfMedicineRepository availabilityOfMedicineRepository,
                                         PharmacyService pharmacyService,
                                         MedicineService medicineService) {
        this.availabilityOfMedicineRepository = availabilityOfMedicineRepository;
        this.pharmacyService = pharmacyService;
        this.medicineService = medicineService;
    }

    public void save(AvailabilityOfMedicine availabilityOfMedicine) {
        if (checkForeignKeys(availabilityOfMedicine.getPharmacyId(), availabilityOfMedicine.getMedicineId())) {
            availabilityOfMedicineRepository.save(availabilityOfMedicine);
        }
    }

    public AvailabilityOfMedicine findById(Integer id) {
        return availabilityOfMedicineRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Данные о наличии препарата не найдены!")
        );

    }

    public List<AvailabilityOfMedicine> findAll() {
        return availabilityOfMedicineRepository.findAll();
    }

    public void update(AvailabilityOfMedicine availabilityOfMedicine) {
        if (findById(availabilityOfMedicine.getId()) != null &&
                checkForeignKeys(availabilityOfMedicine.getPharmacyId(), availabilityOfMedicine.getMedicineId())) {
            availabilityOfMedicineRepository.update(availabilityOfMedicine);
        }
    }

    public List<AvailabilityOfMedicine> filter(String search) {
        return availabilityOfMedicineRepository.filter(search);
    }

    public List<AvailabilityOfMedicine> sort(String sort, String comparator) {

        return availabilityOfMedicineRepository.sort(sort, comparator);
    }

    public void delete(Integer id) {
        availabilityOfMedicineRepository.delete(id);
    }

    private boolean checkForeignKeys(Integer pharmacyId, Integer medicineId) {
        return pharmacyService.findById(pharmacyId) != null && medicineService.findById(medicineId) != null;
    }
}
