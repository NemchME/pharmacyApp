package org.example.model;

import java.sql.Timestamp;
import java.util.Objects;

public class PharmacyInfo {

    private String medicineName;
    private float price;
    private int quantity;
    private Timestamp updatedAt;

    public PharmacyInfo() {
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PharmacyInfo{" +
                "medicineName='" + medicineName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyInfo that = (PharmacyInfo) o;
        return Float.compare(price, that.price) == 0 && quantity == that.quantity && Objects.equals(medicineName, that.medicineName) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineName, price, quantity, updatedAt);
    }
}
