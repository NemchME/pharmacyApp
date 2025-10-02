package org.example.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Order {
    private Integer id;
    private Integer userId;
    private Integer medicineId;
    private Integer pharmacyId;
    private Integer quantily;
    private String status;
    private Timestamp createdAt;

    public Order(Integer id, Integer userId, Integer medicineId, Integer pharmacyId, Integer quantily, String status) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.quantily = quantily;
        this.status = status;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public Order(Integer userId, Integer medicineId, Integer pharmacyId, Integer quantily,
                 String status) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.quantily = quantily;
        this.status = status;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Integer getQuantily() {
        return quantily;
    }

    public void setQuantily(Integer quantily) {
        this.quantily = quantily;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", medicineId=" + medicineId +
                ", pharmacyId=" + pharmacyId +
                ", quantily=" + quantily +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(userId, order.userId) &&
                Objects.equals(medicineId, order.medicineId) && Objects.equals(pharmacyId, order.pharmacyId) &&
                Objects.equals(quantily, order.quantily) && Objects.equals(status, order.status) &&
                Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicineId, pharmacyId, quantily, status, createdAt);
    }
}
