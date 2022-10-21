package com.solvd.airport.domain.carrier;

public class License {

    private Long id;
    private Integer number;
    private String type;

    public String toString() {
        return ("Licence: id: " + this.id + " number: " + this.number + " type: " + this.type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
