package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.port.Gate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GateRepository {

    void create(Gate gate);

    List<Gate> readAll();

    Gate readById(Long id);

    void update(Gate gate);

    void deleteById(Long id);
}