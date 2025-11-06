package org.example.service;

import org.example.repository.impl.jdbc.StatsRepository;

import java.util.Map;

public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public Map<String, Integer> getTableCounts() {
        return statsRepository.getTableCounts();
    }
}
