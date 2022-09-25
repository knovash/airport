package com.solvd.airport.domain.flight;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.port.Gate;

import java.math.BigDecimal;

public class Ticket {

    private Long id;
    private BigDecimal flight_number;
    private Passenger passenger;
    private BigDecimal gate_number;
    private BigDecimal price;
    private Integer number;
    private Integer seat;

    public String toString() {
        return ("Ticket: id: " + this.id
                + " flight_number: " + this.flight_number
                + " gate_number: " + this.gate_number
                + " price: " + this.price
                + " number: " + this.number
                + " seat: " + this.seat
                + "\n  passenger: " + this.passenger
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public BigDecimal getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(BigDecimal flight_number) {
        this.flight_number = flight_number;
    }

    public BigDecimal getGate_number() {
        return gate_number;
    }

    public void setGate_number(BigDecimal gate_number) {
        this.gate_number = gate_number;
    }
}
