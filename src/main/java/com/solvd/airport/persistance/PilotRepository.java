package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Pilot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PilotRepository {

    void create(Pilot pilot, Long aircarrier_id);

    List<Pilot> readAll();

    Pilot map(ResultSet resultSet) throws SQLException;

    Pilot readById(Long id);

    void update(Pilot pilot);

    void deleteById(Long id);
}