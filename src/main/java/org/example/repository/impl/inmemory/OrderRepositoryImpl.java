package org.example.repository.impl.inmemory;

import org.example.model.Order;
import org.example.model.OrderInfo;
import org.example.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private final Map<Integer, Order> storageMap = new HashMap<>();
    private Integer idCounter = 0;

    @Override
    public void save(Order entity) {
        entity.setId(idCounter);
        storageMap.put(idCounter++, entity);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(storageMap.get(id));
    }

    @Override
    public List<Order> findAll() {
        return storageMap.values().stream().toList();
    }

    @Override
    public List<Order> filter(String search) {
        return List.of();
    }

    @Override
    public List<Order> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public int countAll() {
        return storageMap.size();
    }

    @Override
    public List<Order> sort(String sort, String comparator) {
        return List.of();
    }

    @Override
    public void update(Order entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        storageMap.remove(id);
    }

    @Override
    public List<OrderInfo> findByUserId(Integer userId) {
        return List.of();
    }
}
