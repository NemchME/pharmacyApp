package org.example.model;

import java.util.Objects;

public class Pharmacy {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String workingHours;
    private String wayFromCenter;

    public Pharmacy(Integer id, String name, String address, String phone, String workingHours, String wayFromCenter) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.workingHours = workingHours;
        this.wayFromCenter = wayFromCenter;
    }

    public Pharmacy(String name, String address, String phone, String workingHours, String wayFromCenter) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.workingHours = workingHours;
        this.wayFromCenter = wayFromCenter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWayFromCenter() {
        return wayFromCenter;
    }

    public void setWayFromCenter(String wayFromCenter) {
        this.wayFromCenter = wayFromCenter;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", wayFromCenter='" + wayFromCenter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(id, pharmacy.id) && Objects.equals(name, pharmacy.name) &&
                Objects.equals(address, pharmacy.address) && Objects.equals(phone, pharmacy.phone) &&
                Objects.equals(workingHours, pharmacy.workingHours) &&
                Objects.equals(wayFromCenter, pharmacy.wayFromCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, workingHours, wayFromCenter);
    }
}
