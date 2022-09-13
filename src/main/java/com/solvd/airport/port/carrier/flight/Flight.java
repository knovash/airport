package com.solvd.airport.port.carrier.flight;

import com.solvd.airport.port.carrier.AirCraft;
import com.solvd.airport.port.carrier.Pilot;
import com.solvd.airport.port.carrier.flight.passenger.Passenger;
import com.solvd.airport.port.AirStrip;

import java.time.LocalDate;
import java.util.List;

public class Flight {
    private Long id;
    private Integer flightNumber;
    private AirCraft flightAirCraft;
    private AirStrip airStrip;
    private LocalDate flightDate;
    private Direction flightDirection;
    private List<Ticket> tickets;
    private List<Pilot> flightPilots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public AirCraft getFlightAirCraft() {
        return flightAirCraft;
    }

    public void setFlightAirCraft(AirCraft flightAirCraft) {
        this.flightAirCraft = flightAirCraft;
    }

    public AirStrip getAirStrip() {
        return airStrip;
    }

    public void setAirStrip(AirStrip airStrip) {
        this.airStrip = airStrip;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public Direction getFlightDirection() {
        return flightDirection;
    }

    public void setFlightDirection(Direction flightDirection) {
        this.flightDirection = flightDirection;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Pilot> getFlightPilots() {
        return flightPilots;
    }

    public void setFlightPilots(List<Pilot> flightPilots) {
        this.flightPilots = flightPilots;
    }
}
