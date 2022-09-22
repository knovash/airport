package com.solvd.airport.persistance;

import com.solvd.airport.domain.port.Airstrip;

import java.util.List;

public interface AirstripRepository {

    void create(Airstrip airstrip);

    List<Airstrip> readAll();

    Airstrip readById(Long id);

    List<Airstrip> readByAirportId(Long airportId);

    void update(Airstrip airstrip);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}