package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AircarrierRepository {

    void create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

    Aircarrier readById(Long id);

    void update(Aircarrier aircarrier);

    void deleteById(Long id);
}