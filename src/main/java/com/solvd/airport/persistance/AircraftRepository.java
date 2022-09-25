package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AircraftRepository {

    void create(Aircraft aircraft);

    List<Aircraft> readAll();

    Aircraft map(ResultSet resultSet) throws SQLException;

    Aircraft readById(Long id);

    void update(Aircraft aircraft);

    void deleteById(Long id);
}