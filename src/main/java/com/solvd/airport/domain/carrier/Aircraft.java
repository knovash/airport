package com.solvd.airport.domain.carrier;

public class Aircraft {

    private Long id;
    private Integer number;
    private String model;

    public String toString() {
        return ("Aircraft: id: " + this.id + " number: " + this.number + " model: " + this.model);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
