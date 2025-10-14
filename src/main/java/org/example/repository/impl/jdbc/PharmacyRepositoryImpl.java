package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.Pharmacy;
import org.example.repository.PharmacyRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PharmacyRepositoryImpl implements PharmacyRepository {

    private final Connection connection;

    public PharmacyRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void save(Pharmacy entity) {
        String sql = "INSERT INTO pharmacy (name, address, phone, working_hours, way_from_center) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getWorkingHours());
            ps.setString(5, entity.getWayFromCenter());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Pharmacy> findById(Integer id) {
        String sql = "SELECT * FROM pharmacy WHERE id = ?";
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
    public List<Pharmacy> findAll() {
        List<Pharmacy> pharmacyList = new ArrayList<>();
        String sql = "SELECT * FROM pharmacy";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                pharmacyList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return pharmacyList;
    }


    @Override
    public void update(Pharmacy entity) {
        String sql = "UPDATE pharmacy SET name=?, address=?, phone=?, working_hours=?, way_from_center=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getWorkingHours());
            ps.setString(5, entity.getWayFromCenter());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM pharmacy WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private Pharmacy mapRow(ResultSet rs) throws SQLException {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(rs.getInt("id"));
        pharmacy.setName(rs.getString("name"));
        pharmacy.setAddress(rs.getString("address"));
        pharmacy.setPhone(rs.getString("phone"));
        pharmacy.setWorkingHours(rs.getString("working_hours"));
        pharmacy.setWayFromCenter(rs.getString("way_from_center"));
        return pharmacy;
    }
}
