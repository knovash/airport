package com.solvd.airport.port;

public class AirStrip {
    private Long id;
    private AirPort airPort;
    private Integer airStripNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirPort getAirPort() {
        return airPort;
    }

    public void setAirPort(AirPort airPort) {
        this.airPort = airPort;
    }

    public Integer getAirStripNumber() {
        return airStripNumber;
    }

    public void setAirStripNumber(Integer airStripNumber) {
        this.airStripNumber = airStripNumber;
    }
}
