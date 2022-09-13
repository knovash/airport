package com.solvd.airport.port;

public class Gate {
    private Long id;
    private AirPort airPort;
    private Integer gateNumber;

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

    public Integer getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(Integer gateNumber) {
        this.gateNumber = gateNumber;
    }
}
