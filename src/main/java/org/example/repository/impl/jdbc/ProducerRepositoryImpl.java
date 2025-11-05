package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.Producer;
import org.example.repository.ProducerRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerRepositoryImpl implements ProducerRepository {

    private final Connection connection;

    public ProducerRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void save(Producer entity) {
        String sql = "INSERT INTO producer (name, country) " +
                "VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCountry());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producer> findById(Integer id) {
        String sql = "SELECT * FROM producer WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Producer> findAll() {
        List<Producer> producerList = new ArrayList<>();
        String sql = "SELECT * FROM producer";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                producerList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return producerList;
    }

    @Override
    public List<Producer> filter(String search) {
        List<Producer> producers = new ArrayList<>();
        String sql = "SELECT * FROM producer " +
                "WHERE LOWER(CAST(id AS VARCHAR)) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?) " +
                "OR LOWER(country) LIKE LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String pattern = "%" + search + "%";
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, pattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    producers.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при поиске производителей: " + e.getMessage(), e);
        }
        return producers;
    }

    @Override
    public List<Producer> sort(String sort, String comparator) {
        List<Producer> producers = new ArrayList<>();

        String orderBy = switch (sort) {
            case "name" -> "name";
            case "country" -> "country";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM producer " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                producers.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке производителей: " + e.getMessage(), e);
        }
        return producers;
    }


    @Override
    public void update(Producer entity) {
        String sql = "UPDATE producer SET name=?, country=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM producer WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private Producer mapRow(ResultSet rs) throws SQLException {
        Producer producer = new Producer();
        producer.setId(rs.getInt("id"));
        producer.setCountry(rs.getString("country"));
        return producer;
    }
}
