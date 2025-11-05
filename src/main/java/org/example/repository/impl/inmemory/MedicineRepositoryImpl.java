package org.example.repository.impl.inmemory;

import org.example.model.Medicine;
import org.example.repository.MedicineRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MedicineRepositoryImpl implements MedicineRepository {

    private final Map<Integer, Medicine> storageMap = new HashMap<>();
    private Integer idCounter = 0;


    @Override
    public void save(Medicine entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<Medicine> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<Medicine> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public List<Medicine> filter(String search) {
        return List.of();
    }

    @Override
    public List<Medicine> sort(String sort, String comparator) {
        return List.of();
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
