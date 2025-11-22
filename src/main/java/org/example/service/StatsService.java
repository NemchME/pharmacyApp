package org.example.service;

import org.example.repository.impl.jdbc.StatsRepositoryImpl;

import java.util.Map;

public class StatsService {

    private final StatsRepositoryImpl statsRepository;

    public StatsService(StatsRepositoryImpl statsRepository) {
        this.statsRepository = statsRepository;
    }

    public Map<String, Integer> getTableCounts() {
        return statsRepository.getTableCounts();
    }
}
