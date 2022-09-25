package com.solvd.airport.persistance;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.passenger.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface FlightRepository {

    void create(Flight flight);

    List<Flight> readAll();

    Flight map(ResultSet resultSet) throws SQLException;

    Flight readById(Long id);

    List<Flight> readByPilotId(Long pilotId);

    List<Flight> readByAircarrierId(Long aircarrierId);

    void update(Flight flight);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}