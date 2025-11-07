package org.example.model;

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
}
