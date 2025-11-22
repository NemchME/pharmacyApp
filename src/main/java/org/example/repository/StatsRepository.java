package org.example.repository;

import java.util.Map;

public interface StatsRepository {

    Map<String, Integer> getTableCounts();
}
