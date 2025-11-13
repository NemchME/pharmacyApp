package org.example.model;

import java.sql.Timestamp;
import java.util.Objects;

public class OrderInfo {

    private String medicineName;
    private String pharmacyName;
    private Integer quantity;
    private String status;
    private Timestamp createdAt;

    public OrderInfo(String medicineName, String pharmacyName, Integer quantity, String status, Timestamp createdAt) {
        this.medicineName = medicineName;
        this.pharmacyName = pharmacyName;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public OrderInfo() {
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "medicineName='" + medicineName + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(medicineName, orderInfo.medicineName) && Objects.equals(pharmacyName, orderInfo.pharmacyName) && Objects.equals(quantity, orderInfo.quantity) && Objects.equals(status, orderInfo.status) && Objects.equals(createdAt, orderInfo.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineName, pharmacyName, quantity, status, createdAt);
    }
}
