package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.sql.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void save(User entity) {
        String sql = "INSERT INTO users (username, password_hash, email, role) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPasswordHash());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
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
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                userList.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return userList;
    }

    public List<User> findAll(int page, int size) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userList.add(mapRow(rs));
                }
            }

            return userList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM users";
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
    public List<User> filter(String search) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users " +
                "WHERE LOWER(CAST(id AS VARCHAR)) LIKE LOWER(?) OR LOWER(username) LIKE LOWER(?) " +
                "OR LOWER(email) LIKE LOWER(?) OR LOWER(role) LIKE LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String pattern = "%" + search + "%";

            for (int i = 1; i <= 4; i++) {
                ps.setString(i, pattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при поиске пользователей: " + e.getMessage(), e);
        }
        return users;
    }

    @Override
    public List<User> sort(String sort, String comparator) {
        List<User> users = new ArrayList<>();

        String orderBy = switch (sort) {
            case "username" -> "username";
            case "email" -> "email";
            case "role" -> "role";
            default -> "id";
        };

        String direction = "desc".equalsIgnoreCase(comparator) ? "DESC" : "ASC";

        String sql = "SELECT * FROM user " +
                "ORDER BY " + orderBy + " " + direction;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка при сортировке пользователей: " + e.getMessage(), e);
        }
        return users;
    }


    @Override
    public void update(User entity) {
        String sql = "UPDATE users " +
                "SET username=?, password_hash=?, email=?, role=? " +
                "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPasswordHash());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
