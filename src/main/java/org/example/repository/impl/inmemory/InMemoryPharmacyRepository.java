package org.example.repository.impl.inmemory;

import org.example.model.Pharmacy;
import org.example.repository.PharmacyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPharmacyRepository implements PharmacyRepository {

    private final Map<Integer, Pharmacy> storageMap = new HashMap<>();

    @Override
    public void save(Pharmacy entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public Optional<Pharmacy> findById(Integer id) {
        return Optional.of(storageMap.get(id));
    }

    @Override
    public List<Pharmacy> findAll() {
        return storageMap.values().stream().toList();
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
