package org.example.repository;

import org.example.model.AvailabilityInfo;

import java.util.List;

public interface AvailabilityInfoRepository {

    List<AvailabilityInfo> getPharmaciesByMedicineId(Integer medicineId);
}
