package com.solvd.airport.persistence;

import com.solvd.airport.domain.port.Airstrip;

import java.util.List;

public interface AirstripRepository {

    void create(Airstrip airstrip, Long airportId);

    List<Airstrip> readAll();

    Airstrip readById(Long id);

    void update(Airstrip airstrip, Long airportID);

    void deleteById(Long id);
}
