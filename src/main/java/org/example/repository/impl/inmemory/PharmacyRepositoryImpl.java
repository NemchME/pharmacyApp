package org.example.repository.impl.inmemory;

import org.example.model.Pharmacy;
import org.example.repository.PharmacyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PharmacyRepositoryImpl implements PharmacyRepository {

    private final Map<Integer, Pharmacy> storageMap = new HashMap<>();
    private Integer idCounter = 0;

    @Override
    public void save(Pharmacy entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<Pharmacy> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<Pharmacy> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public List<Pharmacy> filter(String search) {
        return List.of();
    }

    @Override
    public List<Pharmacy> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public int countAll() {
        return storageMap.size();
    }

    @Override
    public List<Pharmacy> sort(String sort, String comparator) {
        return List.of();
    }

    @Override
    public void update(Pharmacy entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }
}
