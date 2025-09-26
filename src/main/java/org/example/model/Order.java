package org.example.model;

import java.sql.Timestamp;

public class Order {
    private Integer id;
    private Integer userId;
    private Integer medicineId;
    private Integer pharmacyId;
    private Integer quantily;
    private String status;
    private Timestamp createdAt;

    public Order(Integer id, Integer userId, Integer medicineId, Integer pharmacyId, Integer quantily,
                 String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.quantily = quantily;
        this.status = status;
        this.createdAt = createdAt;
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
}
