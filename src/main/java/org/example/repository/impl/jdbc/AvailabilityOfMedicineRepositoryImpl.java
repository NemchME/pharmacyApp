package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.AvailabilityOfMedicine;
import org.example.repository.AvailabilityOfMedicineRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailabilityOfMedicineRepositoryImpl implements AvailabilityOfMedicineRepository {

    private final Connection connection;

    public AvailabilityOfMedicineRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void save(AvailabilityOfMedicine entity) {
        String sql = "INSERT INTO availability_of_medicine (pharmacy_id, medicine_id, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getPharmacyId());
            ps.setInt(2, entity.getMedicineId());
            ps.setFloat(3, entity.getPrice());
            ps.setInt(4, entity.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<AvailabilityOfMedicine> findById(Integer id) {
        String sql = "SELECT * FROM availability_of_medicine WHERE id = ?";
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
    public List<AvailabilityOfMedicine> findAll() {
        List<AvailabilityOfMedicine> availabilityOfMedicineList = new ArrayList<>();
        String sql = "SELECT * FROM availability_of_medicine";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                availabilityOfMedicineList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return availabilityOfMedicineList;
    }


    @Override
    public void update(AvailabilityOfMedicine entity) {
        String sql = "UPDATE availability_of_medicine SET pharmacy_id=?, medicine_id=?, price=?, quantity=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getPharmacyId());
            ps.setInt(2, entity.getMedicineId());
            ps.setFloat(3, entity.getPrice());
            ps.setInt(4, entity.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM availability_of_medicine WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private AvailabilityOfMedicine mapRow(ResultSet rs) throws SQLException {
        AvailabilityOfMedicine availabilityOfMedicine = new AvailabilityOfMedicine();
        availabilityOfMedicine.setId(rs.getInt("id"));
        availabilityOfMedicine.setPharmacyId(rs.getInt("pharmacy_id"));
        availabilityOfMedicine.setMedicineId(rs.getInt("medicine_id"));
        availabilityOfMedicine.setPrice(rs.getFloat("price"));
        availabilityOfMedicine.setQuantity(rs.getInt("quantity"));
        availabilityOfMedicine.setUpdatedAt(rs.getTimestamp("updated_at"));
        return availabilityOfMedicine;
    }
}
