package org.example.repository.impl.inmemory;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final Map<Integer, User> storageMap = new HashMap<>();
    private Integer idCounter = 0;

    @Override
    public void save(User entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<User> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public void update(User entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }
}
