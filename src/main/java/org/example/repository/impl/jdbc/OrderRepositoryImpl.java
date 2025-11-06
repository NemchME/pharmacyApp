package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private final Connection connection;

    public OrderRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    public List<Order> findAll(int page, int size) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orderList.add(mapRow(rs));
                }
            }

            return orderList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM orders";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при подсчёте записей: " + e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public void save(Order entity) {
        String sql = "INSERT INTO orders (user_id, medicine_id, pharmacy_id, quantity, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getUserId());
            ps.setInt(2, entity.getMedicineId());
            ps.setInt(3, entity.getPharmacyId());
            ps.setInt(4, entity.getQuantity());
            ps.setString(5, entity.getStatus());
            ps.setTimestamp(6, entity.getCreatedAt());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Order> findById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
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
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                orderList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return orderList;
    }

    @Override
    public List<Order> filter(String search) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders " +
                "WHERE LOWER(CAST(id AS VARCHAR)) LIKE LOWER(?) OR LOWER(CAST(user_id AS VARCHAR)) LIKE LOWER(?) " +
                "OR LOWER(CAST(medicine_id AS VARCHAR)) LIKE LOWER(?) OR LOWER(CAST(pharmacy_id AS VARCHAR)) LIKE LOWER(?) " +
                "OR LOWER(CAST(quantity AS VARCHAR)) LIKE LOWER(?) OR LOWER(status) LIKE LOWER(?) " +
                "OR LOWER(CAST(created_at AS VARCHAR)) LIKE LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String pattern = "%" + search + "%";

            for (int i = 1; i <= 7; i++) {
                ps.setString(i, pattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при поиске заказов: " + e.getMessage(), e);
        }
        return orders;
    }

    @Override
    public List<Order> sort(String sort, String comparator) {
        List<Order> orders = new ArrayList<>();


        String orderBy = switch (sort) {
            case "userId" -> "user_id";
            case "medicineId" -> "medicine_id";
            case "pharmacyId" -> "pharmacy_id";
            case "quantity" -> "quantity";
            case "status" -> "status";
            case "createdAt" -> "created_at";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM orders " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orders.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке заказов: " + e.getMessage(), e);
        }
        return orders;
    }


    @Override
    public void update(Order entity) {
        String sql = "UPDATE orders SET user_id=?, medicine_id=?, pharmacy_id=?, quantity=?, status=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getUserId());
            ps.setInt(2, entity.getMedicineId());
            ps.setInt(3, entity.getPharmacyId());
            ps.setInt(4, entity.getQuantity());
            ps.setString(5, entity.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setMedicineId(rs.getInt("medicine_id"));
        order.setPharmacyId(rs.getInt("pharmacy_id"));
        order.setQuantity(rs.getInt("quantity"));
        order.setStatus(rs.getString("status"));
        order.setCreatedAt(rs.getTimestamp("created_at"));
        return order;
    }
}
