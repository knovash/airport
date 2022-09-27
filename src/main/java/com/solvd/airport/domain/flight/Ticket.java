package com.solvd.airport.domain.flight;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.port.Gate;

import java.math.BigDecimal;

public class Ticket {

    private Long id;
    private Flight flight;
    private Passenger passenger;
    private Gate gate;
    private BigDecimal price;
    private Integer number;
    private Integer seat;

    public String toString() {
        return ("Ticket: id: " + this.id
                + " price: " + this.price
                + " seat: " + this.seat
                + "\n  gate : " + this.gate
                + "\n  passenger: " + this.passenger
                + "\n  flight : " + this.flight
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}
