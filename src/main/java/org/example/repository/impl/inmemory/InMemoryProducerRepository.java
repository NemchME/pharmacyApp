package org.example.repository.impl.inmemory;

import org.example.model.Producer;
import org.example.repository.ProducerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryProducerRepository implements ProducerRepository {

    private final Map<Integer, Producer> storageMap = new HashMap<>();
    private Integer idCounter = 0;
    @Override
    public void save(Producer entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<Producer> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<Producer> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public void update(Producer entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }
}
