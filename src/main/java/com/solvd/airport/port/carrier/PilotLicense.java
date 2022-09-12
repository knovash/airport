package com.solvd.airport.port.carrier;

public class PilotLicense {
    private Long id;
    private Integer licenseNumber;
    private String licenseType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
}
