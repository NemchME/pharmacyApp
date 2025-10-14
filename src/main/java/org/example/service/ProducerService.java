package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.model.Producer;
import org.example.repository.ProducerRepository;

import java.util.List;

public class ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    public Producer findById(Integer id) {
        return producerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Производитель с id '" + id + "' не найден!")
        );
    }

    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    public void update(Producer producer) {
        if (findById(producer.getId()) != null) {
            producerRepository.update(producer);
        }
    }

    public void delete(Integer id) {
        producerRepository.delete(id);
    }
}
