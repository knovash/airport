package com.solvd.airport.domain.flight;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.port.Airstrip;

import java.sql.Date;
import java.util.List;

public class Flight {

    private Long id;
    private Aircraft aircraft;
    private Airstrip airstrip;
    private Pilot pilot;
    private Direction direction;
    private Integer number;
    private Date date;
    private List<Ticket> tickets;

    public String toString() {
        return ("Flight: id: " + this.id
                + " number: " + this.number
                + " date: " + this.date
                + "\n  aircraft: " + this.aircraft
                + "\n  airstrip: " + this.airstrip
                + "\n  pilot: " + this.pilot
                + "\n  direction: " + this.direction
                + "\n  tickets: " + this.tickets
        );
    }

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

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
