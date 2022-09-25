package com.solvd.airport.persistance;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.port.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AirportRepository {

    void create(Airport airport);

    List<Airport> readAll();

    Airport map(ResultSet resultSet) throws SQLException;

    Airport readById(Long id);

    void update(Airport airport);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}