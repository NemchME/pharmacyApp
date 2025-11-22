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
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                pharmacyList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return pharmacyList;
    }

    public List<Pharmacy> findAll(int page, int size) {
        List<Pharmacy> pharmacyList = new ArrayList<>();
        String sql = "SELECT * FROM pharmacy LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pharmacyList.add(mapRow(rs));
                }
            }
            return pharmacyList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM pharmacy";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при подсчёте записей: " + e.getMessage(), e);
        }
        return 0;
    }


    @Override
    public List<Pharmacy> filter(String search) {
        List<Pharmacy> pharmacies = new ArrayList<>();
        String sql = "SELECT * FROM pharmacy " +
                "WHERE LOWER(CAST(id AS VARCHAR)) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?) " +
                "OR LOWER(address) LIKE LOWER(?) OR LOWER(phone) LIKE LOWER(?) " +
                "OR LOWER(working_hours) LIKE LOWER(?) OR LOWER(way_from_center) LIKE LOWER(?)";

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
            throw new DBException("Ошибка при поиске аптек: " + e.getMessage(), e);
        }
        return pharmacies;
    }

    @Override
    public List<Pharmacy> sort(String sort, String comparator) {
        List<Pharmacy> pharmacies = new ArrayList<>();

        String orderBy = switch (sort) {
            case "name" -> "name";
            case "address" -> "address";
            case "phone" -> "phone";
            case "workingHours" -> "working_hours";
            case "wayFromCenter" -> "way_from_center";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM pharmacy " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pharmacies.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке аптек: " + e.getMessage(), e);
        }
        return pharmacies;
    }


    @Override
    public void update(Pharmacy entity) {
        String sql = "UPDATE pharmacy " +
                "SET name=?, address=?, phone=?, working_hours=?, way_from_center=? " +
                "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getWorkingHours());
            ps.setString(5, entity.getWayFromCenter());
            ps.setInt(6, entity.getId());
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
