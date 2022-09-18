package com.solvd.airport.domain.flight;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.port.Airstrip;

import java.time.LocalDate;
import java.util.List;

public class Flight {

    private Long id;
    private Aircraft aircraft;
    private Airstrip airstrip;
    private Direction direction;
    private Integer number;
    private LocalDate date;
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airstrip getAirstrip() {
        return airstrip;
    }

    public void setAirstrip(Airstrip airstrip) {
        this.airstrip = airstrip;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
