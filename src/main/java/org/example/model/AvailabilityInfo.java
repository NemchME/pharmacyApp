package org.example.model;

import java.util.Objects;

public class AvailabilityInfo {
    
    private String pharmacyName;
    private String wayFromCenter;
    private int quantity;
    private float price;

    public AvailabilityInfo() {
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getWayFromCenter() {
        return wayFromCenter;
    }

    public void setWayFromCenter(String wayFromCenter) {
        this.wayFromCenter = wayFromCenter;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AvailabilityInfo{" +
                "pharmacyName='" + pharmacyName + '\'' +
                ", wayFromCenter='" + wayFromCenter + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityInfo that = (AvailabilityInfo) o;
        return quantity == that.quantity && Float.compare(price, that.price) == 0 && Objects.equals(pharmacyName, that.pharmacyName) && Objects.equals(wayFromCenter, that.wayFromCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacyName, wayFromCenter, quantity, price);
    }
}
