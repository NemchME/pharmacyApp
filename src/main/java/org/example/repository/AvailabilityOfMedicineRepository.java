package org.example.repository;

import org.example.model.AvailabilityInfo;
import org.example.model.AvailabilityOfMedicine;
import org.example.model.PharmacyInfo;

import java.util.List;

public interface AvailabilityOfMedicineRepository extends CrudRepository<AvailabilityOfMedicine, Integer> {

    List<AvailabilityInfo> findByMedicineId(Integer medicineId);
    List<PharmacyInfo> findByPharmacyId(Integer pharmacyId);
}
