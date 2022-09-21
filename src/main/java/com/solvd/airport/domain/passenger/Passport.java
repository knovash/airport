package com.solvd.airport.domain.passenger;

public class Passport {

    private Long id;
    private Integer number;

    public String toString() {
        return ("Passport: id: " + this.id + " number: " + this.number);
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
}
