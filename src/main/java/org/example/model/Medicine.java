package org.example.model;

public class Medicine {
    private Integer id;
    private String tradeName;
    private String inn;
    private String dosage;
    private String form;
    private Integer producerId;

    public Medicine(int id, String tradeName, String inn, String dosage, String form, int producerId) {
        this.id = id;
        this.tradeName = tradeName;
        this.inn = inn;
        this.dosage = dosage;
        this.form = form;
        this.producerId = producerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", tradeName='" + tradeName + '\'' +
                ", inn='" + inn + '\'' +
                ", dosage='" + dosage + '\'' +
                ", form='" + form + '\'' +
                ", producerId=" + producerId +
                '}';
    }
}
