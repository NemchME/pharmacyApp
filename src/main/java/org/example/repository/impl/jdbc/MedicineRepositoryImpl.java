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

    public List<Medicine> findAll(int page, int size) {
        List<Medicine> medicineList = new ArrayList<>();
        String sql = "SELECT * FROM medicine LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    medicineList.add(mapRow(rs));
                }
            }

            return medicineList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM medicine";
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
    public List<Medicine> filter(String search) {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine " +
                "WHERE LOWER(trade_name) LIKE LOWER(?) OR LOWER(inn) LIKE LOWER(?) " +
                "OR LOWER(dosage) LIKE LOWER(?) OR LOWER(form) LIKE LOWER(?) " +
                "OR LOWER(CAST(producer_id AS VARCHAR)) LIKE LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String pattern = "%" + search + "%";

            for (int i = 1; i <= 5; i++) {
                ps.setString(i, pattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    medicines.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при поиске препаратов: " + e.getMessage(), e);
        }
        return medicines;
    }

    @Override
    public List<Medicine> sort(String sort, String comparator) {
        List<Medicine> medicines = new ArrayList<>();

        String orderBy = switch (sort) {
            case "tradeName" -> "trade_name";
            case "inn" -> "inn";
            case "dosage" -> "dosage";
            case "form" -> "form";
            case "producerId" -> "producer_id";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM medicine " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                medicines.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке препаратов: " + e.getMessage(), e);
        }
        return medicines;
    }


    @Override
    public void update(Medicine entity) {
        String sql = "UPDATE medicine " +
                "SET trade_name=?, inn=?, dosage=?, form=?, producer_id=? " +
                "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getTradeName());
            ps.setString(2, entity.getInn());
            ps.setString(3, entity.getDosage());
            ps.setString(4, entity.getForm());
            ps.setInt(5, entity.getProducerId());
            ps.setInt(6, entity.getId());
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
