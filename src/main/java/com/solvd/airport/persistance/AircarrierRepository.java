package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircarrier;

import java.util.List;

public interface AircarrierRepository {

    void create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

    Aircarrier readById(Long id);

    List<Aircarrier> readByAirportId(Long airportId);

    void update(Aircarrier aircarrier);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}