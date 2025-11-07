package org.example.service;

import org.example.model.AvailabilityInfo;
import org.example.repository.AvailabilityInfoRepository;

import java.util.List;

public class AvailabilityInfoService {

    private final AvailabilityInfoRepository availabilityInfoRepository;

    public AvailabilityInfoService(AvailabilityInfoRepository availabilityInfoRepository) {
        this.availabilityInfoRepository = availabilityInfoRepository;
    }

    public List<AvailabilityInfo> getPharmaciesByMedicineId(Integer medicineId) {
        return availabilityInfoRepository.getPharmaciesByMedicineId(medicineId);
    }
}