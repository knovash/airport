package com.solvd.airport.persistence;

import com.solvd.airport.domain.carrier.Aircarrier;

import java.util.List;

public interface AircarrierRepository {

    void create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

    Aircarrier readById(Long id);

    void update(Aircarrier aircarrier);

    void deleteById(Long id);
}
