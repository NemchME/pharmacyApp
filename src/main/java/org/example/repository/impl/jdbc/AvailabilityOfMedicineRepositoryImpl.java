package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.*;
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

    public List<AvailabilityOfMedicine> findAll(int page, int size) {
        List<AvailabilityOfMedicine> availabilityOfMedicineList = new ArrayList<>();
        String sql = "SELECT * FROM availability_of_medicine LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    availabilityOfMedicineList.add(mapRow(rs));
                }
            }

            return availabilityOfMedicineList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM availability_of_medicine";
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
    public List<AvailabilityOfMedicine> filter(String search) {
        List<AvailabilityOfMedicine> pharmacies = new ArrayList<>();
        String sql = "SELECT * FROM availability_of_medicine " +
                "WHERE LOWER(CAST(id AS VARCHAR)) LIKE LOWER(?) OR LOWER(CAST(pharmacy_id AS VARCHAR)) LIKE LOWER(?) " +
                "OR LOWER(CAST(medicine_id AS VARCHAR)) LIKE LOWER(?) OR LOWER(CAST(price AS VARCHAR)) LIKE LOWER(?) " +
                "OR LOWER(CAST(quantity AS VARCHAR)) LIKE LOWER(?) OR LOWER(CAST(updated_at AS VARCHAR)) LIKE LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String pattern = "%" + search + "%";

            for (int i = 1; i <= 6; i++) {
                ps.setString(i, pattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pharmacies.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при поиске доступности препаратов: " + e.getMessage(), e);
        }
        return pharmacies;
    }

    @Override
    public List<AvailabilityOfMedicine> sort(String sort, String comparator) {
        List<AvailabilityOfMedicine> pharmacies = new ArrayList<>();

        String orderBy = switch (sort) {
            case "pharmacyId" -> "pharmacy_id";
            case "medicineId" -> "medicine_id";
            case "price" -> "price";
            case "quantity" -> "quantity";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM availability_of_medicine " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pharmacies.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке доступности препаратов: " + e.getMessage(), e);
        }
        return pharmacies;
    }


    @Override
    public void update(AvailabilityOfMedicine entity) {
        String sql = "UPDATE availability_of_medicine " +
                "SET pharmacy_id=?, medicine_id=?, price=?, quantity=? " +
                "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entity.getPharmacyId());
            ps.setInt(2, entity.getMedicineId());
            ps.setFloat(3, entity.getPrice());
            ps.setInt(4, entity.getQuantity());
            ps.setInt(5, entity.getId());
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

    @Override
    public List<AvailabilityInfo> findByMedicineId(Integer medicineId) {
        List<AvailabilityInfo> availabilityInfoList = new ArrayList<>();

        String sql = """
        SELECT p.name AS pharmacy_name,
               p.way_from_center,
               a.quantity,
               a.price
        FROM availability_of_medicine a
        JOIN pharmacy p ON a.pharmacy_id = p.id
        WHERE a.medicine_id = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AvailabilityInfo availabilityInfo = new AvailabilityInfo();
                availabilityInfo.setPharmacyName(rs.getString("pharmacy_name"));
                availabilityInfo.setWayFromCenter(rs.getString("way_from_center"));
                availabilityInfo.setQuantity(rs.getInt("quantity"));
                availabilityInfo.setPrice(rs.getFloat("price"));
                availabilityInfoList.add(availabilityInfo);
            }

            return availabilityInfoList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public List<PharmacyInfo> findByPharmacyId(Integer pharmacyId) {
        List<PharmacyInfo> pharmacyInfoList = new ArrayList<>();

        String sql = """
        SELECT m.trade_name AS medicine_name,
               a.price,
               a.quantity,
               a.updated_at
        FROM availability_of_medicine a
        JOIN medicine m ON a.medicine_id = m.id
        WHERE a.pharmacy_id = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pharmacyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PharmacyInfo pharmacyInfo = new PharmacyInfo();
                pharmacyInfo.setMedicineName(rs.getString("medicine_name"));
                pharmacyInfo.setPrice(rs.getFloat("price"));
                pharmacyInfo.setQuantity(rs.getInt("quantity"));
                pharmacyInfo.setUpdatedAt(rs.getTimestamp("updated_at"));
                pharmacyInfoList.add(pharmacyInfo);
            }

            return pharmacyInfoList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }
}
