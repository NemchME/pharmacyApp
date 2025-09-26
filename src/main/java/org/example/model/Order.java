package org.example.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private int medicineId;
    private int pharmacyId;
    private int quantily;
    private String status;
    private Timestamp createdAt;

    public Order(int id, int userId, int medicineId, int pharmacyId, int quantily, String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.quantily = quantily;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
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
