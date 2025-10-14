package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.Medicine;
import org.example.repository.MedicineRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicineRepositoryImpl implements MedicineRepository {

    private final Connection connection;

    public MedicineRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void save(Medicine entity) {
        String sql = "INSERT INTO medicine (trade_name, inn, dosage, form, producer_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getTradeName());
            ps.setString(2, entity.getInn());
            ps.setString(3, entity.getDosage());
            ps.setString(4, entity.getForm());
            ps.setInt(5, entity.getProducerId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Medicine> findById(Integer id) {
        String sql = "SELECT * FROM medicine WHERE id = ?";
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
    public List<Medicine> findAll() {
        List<Medicine> medicineList = new ArrayList<>();
        String sql = "SELECT * FROM medicine";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                medicineList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return medicineList;
    }


    @Override
    public void update(Medicine entity) {
        String sql = "UPDATE medicine SET trade_name=?, inn=?, dosage=?, form=?, producer_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getTradeName());
            ps.setString(2, entity.getInn());
            ps.setString(3, entity.getDosage());
            ps.setString(4, entity.getForm());
            ps.setInt(5, entity.getProducerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM medicine WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private Medicine mapRow(ResultSet rs) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setId(rs.getInt("id"));
        medicine.setTradeName(rs.getString("trade_name"));
        medicine.setInn(rs.getString("inn"));
        medicine.setDosage(rs.getString("dosage"));
        medicine.setForm(rs.getString("form"));
        medicine.setProducerId(rs.getInt("producer_id"));
        return medicine;
    }
}
