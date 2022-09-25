package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.passenger.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PilotRepository {

    void create(Pilot pilot);

    List<Pilot> readAll();

    Pilot map(ResultSet resultSet) throws SQLException;

    Pilot readById(Long id);

    List<Pilot> readByAircarrierId(Long aircarrierId);

    void update(Pilot pilot);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}