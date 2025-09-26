package org.example.model;

import java.sql.Timestamp;

public class AvailabilityOfMedicine {
        private Integer id;
        private Integer pharmacyId;
        private Integer medicineId;
    private Float price;
    private Integer quantily;
    private Timestamp updatedAt;

    public AvailabilityOfMedicine(Integer id, Integer pharmacyId, Integer medicineId, Float price,
                                  Integer quantily, Timestamp updatedAt) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.price = price;
        this.quantily = quantily;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantily() {
        return quantily;
    }

    public void setQuantily(Integer quantily) {
        this.quantily = quantily;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
