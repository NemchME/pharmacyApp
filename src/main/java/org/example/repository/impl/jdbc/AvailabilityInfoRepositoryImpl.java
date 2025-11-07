package org.example.repository.impl.jdbc;

import org.example.exception.DBException;
import org.example.model.AvailabilityInfo;
import org.example.repository.AvailabilityInfoRepository;
import org.example.sql.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityInfoRepositoryImpl implements AvailabilityInfoRepository {

    private Connection connection;

    public AvailabilityInfoRepositoryImpl(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }
    @Override
    public List<AvailabilityInfo> getPharmaciesByMedicineId(Integer medicineId) {
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
}
