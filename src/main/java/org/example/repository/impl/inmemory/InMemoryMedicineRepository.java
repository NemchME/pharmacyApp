package org.example.repository.impl.inmemory;

import org.example.model.Medicine;
import org.example.repository.MedicineRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMedicineRepository implements MedicineRepository {

    private final Map<Integer, Medicine> storageMap = new HashMap<>();

    @Override
    public void save(Medicine entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public Optional<Medicine> findById(Integer id) {
        return Optional.of(storageMap.get(id));
    }

    @Override
    public List<Medicine> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public void update(Medicine entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }
}
