package org.example.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class AvailabilityOfMedicine {
    private Integer id;
    private Integer pharmacyId;
    private Integer medicineId;
    private Float price;
    private Integer quantily;
    private Timestamp updatedAt;

    public AvailabilityOfMedicine(Integer id, Integer pharmacyId, Integer medicineId, Float price, Integer quantily) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.price = price;
        this.quantily = quantily;
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public AvailabilityOfMedicine(Integer pharmacyId, Integer medicineId, Float price,
                                  Integer quantily) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.price = price;
        this.quantily = quantily;
        this.updatedAt = Timestamp.from(Instant.now());
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

    @Override
    public String toString() {
        return "AvailabilityOfMedicine{" +
                "id=" + id +
                ", pharmacyId=" + pharmacyId +
                ", medicineId=" + medicineId +
                ", price=" + price +
                ", quantily=" + quantily +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityOfMedicine that = (AvailabilityOfMedicine) o;
        return Objects.equals(id, that.id) && Objects.equals(pharmacyId, that.pharmacyId) &&
                Objects.equals(medicineId, that.medicineId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(quantily, that.quantily) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pharmacyId, medicineId, price, quantily, updatedAt);
    }
}
