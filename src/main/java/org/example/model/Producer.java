package org.example.model;

import java.util.Objects;

public class Producer {
    private Integer id;
    private String name;
    private String country;

    public Producer(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Producer(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) && Objects.equals(name, producer.name) &&
                Objects.equals(country, producer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
