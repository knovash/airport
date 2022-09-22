package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.PilotLicense;

import java.util.List;

public interface PilotLicenseRepository {

    void create(PilotLicense pilotLicense);

    List<PilotLicense> readAll();

    PilotLicense readById(Long id);

    void update(PilotLicense pilotLicense);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}