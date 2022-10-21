package com.solvd.airport.domain.flight;

import com.solvd.airport.domain.passenger.Passenger;

import java.math.BigDecimal;

public class Ticket {

    private Long id;
    private Passenger passenger;
    private BigDecimal price;
    private Integer seat;

    public String toString() {
        return ("\nTicket: id: " + this.id
                + " price: " + this.price
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

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
