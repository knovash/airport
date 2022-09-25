package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircarrier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AircarrierRepository {

    void create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

    Aircarrier map(ResultSet resultSet) throws SQLException;

    Aircarrier readById(Long id);

    void update(Aircarrier aircarrier);

    void deleteById(Long id);
}