package com.solvd.airport.persistance;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.port.Gate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GateRepository {

    void create(Gate gate);

    List<Gate> readAll();

    Gate map(ResultSet resultSet) throws SQLException;

    Gate readById(Long id);

    List<Gate> readByAirportId(Long airportId);

    void update(Gate gate);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}