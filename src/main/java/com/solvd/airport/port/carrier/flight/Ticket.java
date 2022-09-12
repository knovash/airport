package com.solvd.airport.port.carrier.flight;

import com.solvd.airport.port.carrier.flight.passenger.Passenger;
import com.solvd.airport.port.Gate;

import java.math.BigDecimal;

public class Ticket {
    private Flight flight;
    private Passenger passenger;
    private BigDecimal price;
    private Integer ticketNumber;
    private Gate gate;
    private Integer seatNumber;
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}
