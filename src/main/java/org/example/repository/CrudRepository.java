package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> filter(String search);
    List<T> sort(String sort, String comparator);
    void update(T entity);
    void delete(ID id);

}
