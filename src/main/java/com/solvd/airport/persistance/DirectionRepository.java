package com.solvd.airport.persistance;

import com.solvd.airport.domain.flight.Direction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DirectionRepository {

    void create(Direction direction);

    List<Direction> readAll();

    Direction map(ResultSet resultSet) throws SQLException;

    Direction readById(Long id);

    void update(Direction direction);

    void deleteById(Long id);
}