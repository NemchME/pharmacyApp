package org.example.repository.impl.inmemory;

import org.example.model.AvailabilityOfMedicine;
import org.example.repository.AvailabilityOfMedicineRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AvailabilityRepositoryImpl implements AvailabilityOfMedicineRepository {

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
    public void update(AvailabilityOfMedicine entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }
}
