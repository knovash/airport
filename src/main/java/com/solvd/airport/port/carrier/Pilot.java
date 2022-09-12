package com.solvd.airport.port.carrier;

public class Pilot {
    private Long id;
    private String name;
    private PilotLicense pilotLicense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PilotLicense getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(PilotLicense pilotLicense) {
        this.pilotLicense = pilotLicense;
    }
}
