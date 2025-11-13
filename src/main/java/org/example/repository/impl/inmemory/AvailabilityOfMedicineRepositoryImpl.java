package org.example.repository.impl.inmemory;

import org.example.model.AvailabilityInfo;
import org.example.model.AvailabilityOfMedicine;
import org.example.model.PharmacyInfo;
import org.example.repository.AvailabilityOfMedicineRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AvailabilityOfMedicineRepositoryImpl implements AvailabilityOfMedicineRepository {

    private final Map<Integer, AvailabilityOfMedicine> storageMap = new HashMap<>();
    private Integer idCounter = 0;

    @Override
    public void save(AvailabilityOfMedicine entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<AvailabilityOfMedicine> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<AvailabilityOfMedicine> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public List<AvailabilityOfMedicine> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public int countAll() {
        return storageMap.size();
    }

    @Override
    public List<AvailabilityOfMedicine> filter(String search) {
        return List.of();
    }

    @Override
    public List<AvailabilityOfMedicine> sort(String sort, String comparator) {
        return List.of();
    }

    @Override
    public void update(AvailabilityOfMedicine entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }

    @Override
    public List<AvailabilityInfo> findByMedicineId(Integer medicineId) {
        return List.of();
    }

    @Override
    public List<PharmacyInfo> findByPharmacyId(Integer pharmacyId) {
        return List.of();
    }
}
