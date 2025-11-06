package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.sql.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatsRepository {

    private final Connection connection;

    public StatsRepository(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    public Map<String, Integer> getTableCounts() {
        Map<String, Integer> counts = new HashMap<>();

        String[] tables = {"availability_of_medicine", "medicine", "orders", "pharmacy", "producer", "users"};
        for (String table : tables) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM " + table);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    counts.put(table, rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e);
            }
        }
        return counts;
    }
}
