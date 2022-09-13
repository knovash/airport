package com.solvd.airport;

import com.solvd.airport.carrier.Pilot;
import com.solvd.airport.flight.Flight;

public class PilotFlight {

    private Long id;
    private Pilot pilot;
    private Flight flight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
