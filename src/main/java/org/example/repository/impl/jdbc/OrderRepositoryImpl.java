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
        return order;
    }
}
